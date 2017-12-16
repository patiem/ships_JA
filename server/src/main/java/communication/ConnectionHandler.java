package communication;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import fleet.Fleet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


class ConnectionHandler {
    private PlayerHandler playerHandler;
    private LanguageVersion languageVersion;
    private SetMultimap<Socket, String> allHits;

    public ConnectionHandler() {
        playerHandler = new PlayerHandler();
        languageVersion = new LanguageVersion();
        allHits = MultimapBuilder.hashKeys().hashSetValues(50).build();
    }

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
        MessageReceiver messageReceiver = new MessageReceiver();
        int roundCounter = 0; //TODO: to remove after implement rest of gameplay

        while(true) {
            Socket currentSocket = playerHandler.getCurrentSocket();
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(currentSocket.getInputStream(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String hit = messageReceiver.receiveMessage(bufferedReader);
            if(!(allHits.containsEntry(currentSocket, hit))) {
                allHits.put(currentSocket, hit);
                Integer toMark = Integer.parseInt(hit);
                Fleet fleet = playerHandler.getCurrentFleet();

                HitChecker hitChecker = new HitChecker(fleet);
                ShotState shotState = hitChecker.checkShot(toMark);

                showInfoAboutCurrentShot(hit, shotState, roundCounter);
                playerHandler.sendMessageToCurrentPlayer(shotState.toString());
                if (!(shotState == ShotState.HIT)) {
                    playerHandler.switchPlayers();
                    //TODO: send message to other player that this is his turn
                }
                roundCounter++; //TODO: to remove after implement rest of gameplay
            }
        }
    }

    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerHandler.currentPlayerName(), hit, shotState));
    }

    private void registerPlayer(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        allHits.put(socket, "100");
        playerHandler.registerPlayer(socket);
    }
}
