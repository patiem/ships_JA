package communication.rename;

import communication.HitChecker;
import communication.MessageReceiver;
import communication.PlayerHandler;
import communication.ShotState;
import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class Game {


    private PlayerHandler playerHandler;
    private Socket currentSocket;
    private Map<Socket, List<String>> allHits;


    public Game(PlayerHandler playerHandler, Map<Socket, List<String>> allHits) {
        this.playerHandler = playerHandler;
        this.allHits = allHits;
    }


    public void handleGameEvent() {
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

    private boolean isShootAlreadyDone(String shoot) {
        List<String> playerShots = allHits.get(currentSocket);
        return playerShots.contains(shoot);
    }


    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerHandler.currentPlayerName(), hit, shotState));
    }


}
