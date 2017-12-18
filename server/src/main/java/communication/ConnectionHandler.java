package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ConnectionHandler {
    private PlayerHandler playerHandler;
    private LanguageVersion languageVersion;
    private Map<Socket, List<String>> allHits;
    private Socket currentSocket;

    public ConnectionHandler() {
        playerHandler = new PlayerHandler();
        languageVersion = new LanguageVersion();
        allHits = new HashMap<>();
    }

    void acceptConnections(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(languageVersion.getServerRunning());
            registerPlayer(serverSocket);
            registerPlayer(serverSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleGameEvent() {
        MessageReceiver messageReceiver = new MessageReceiver();
        int roundCounter = 0;

        while (true) {
            currentSocket = playerHandler.getCurrentSocket();
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(currentSocket.getInputStream(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String hit = messageReceiver.receiveMessage(bufferedReader);
            if (!isShootAlreadyDone(hit)) {
                addShootToList(hit);
                Integer toMark = Integer.parseInt(hit);
                Fleet fleet = playerHandler.getCurrentFleet();

                HitChecker hitChecker = new HitChecker(fleet);
                ShotState shotState = hitChecker.checkShot(toMark);

                showInfoAboutCurrentShot(hit, shotState, roundCounter);
                playerHandler.sendMessageToCurrentPlayer(shotState.toString());
                if (!(shotState == ShotState.HIT)) {
                    playerHandler.switchPlayers();
                }
                roundCounter++;
            }
        }
    }

    private void addShootToList(String hit) {
        List<String> playerShots = allHits.get(currentSocket);
        playerShots.add(hit);
    }


    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerHandler.currentPlayerName(), hit, shotState));
    }

    private void registerPlayer(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        List<String> hits = new ArrayList<>();
        allHits.put(socket, hits);
        playerHandler.registerPlayer(socket);
    }

    private boolean isShootAlreadyDone(String shoot) {
        List<String> playerShots = allHits.get(currentSocket);
        return playerShots.contains(shoot);
    }
}
