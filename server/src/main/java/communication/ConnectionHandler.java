package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ConnectionHandler {
    private PlayerHandler playerHandler = new PlayerHandler();
    private LanguageVersion languageVersion = new LanguageVersion();

    void acceptConnections(int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(languageVersion.getServerRunning());

            registerPlayer(serverSocket);
            registerPlayer(serverSocket);

        } catch (IOException e) {
            e.printStackTrace(); //TODO: add ExceptionHandler
        }
    }



    void handleGameEvent() {

        Socket currentSocket = playerHandler.getCurrentSocket();

        sendFireNotification(currentSocket, "Your turn. Provide the target");
        sendFireNotification(playerHandler.getWaitingPlayerSocket(), "Wait for opponent's move");

        MessageReceiver messageReceiver = new MessageReceiver();
        String message = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(currentSocket.getInputStream(), "UTF-8"));
            message = messageReceiver.receiveMessage(reader);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: ExceptionHandler
        }

        Integer toMark = Integer.parseInt(message);
        Fleet fleet = playerHandler.getCurrentFleet();

        HitChecker hitChecker = new HitChecker(fleet);
        ShotState shotState = hitChecker.checkShot(toMark);


        sendFireNotification(currentSocket, shotState.toString());

        if (shotState == ShotState.HIT) {

            sendFireNotification(playerHandler.getWaitingPlayerSocket(), "Your opponent hit the target. Please wait");
            sendFireNotification(currentSocket, "You hit the target, please continue shooting");
        } else {
            sendFireNotification(currentSocket, "You missed, it's your opponent's turn ");
            sendFireNotification(playerHandler.getWaitingPlayerSocket(), "Your turn. Provide the target");

            playerHandler.switchPlayers();
        }
    }

    private void registerPlayer(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        playerHandler.registerPlayer(socket);
    }

    private void sendFireNotification(Socket socket, String messageToSend) {
        MessageSender messageSender = new MessageSender();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageSender.send(printWriter, messageToSend);
    }
}
