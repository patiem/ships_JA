package engine;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import communication.HitChecker;
import communication.MessageReceiver;
import communication.PlayerTracker;
import communication.ShotState;
import fleet.Fleet;

import java.io.BufferedReader;
import java.net.Socket;


public class Game {


    private PlayerTracker playerTracker;
    private Socket currentSocket;
    private GameState gameState;
    private MessageReceiver messageReceiver;
    private int roundCounter = 0;
    private BufferedReader currentReader;
    private SetMultimap<Socket, String> allHits;


    public Game(PlayerTracker playerTracker, MessageReceiver messageReceiver) {
        this.playerTracker = playerTracker;
        this.allHits = MultimapBuilder.hashKeys().hashSetValues(50).build();
        gameState = GameState.ACTIVE;
        this.messageReceiver = messageReceiver;
    }


    public void runGame() {

//        prepareWhileCondition();

        while (gameState == GameState.ACTIVE) {
            currentSocket = playerTracker.getCurrentSocket();
            currentReader = playerTracker.getCurrentPlayerClient().getReader();
            checkShot();
        }
    }

//    private void prepareWhileCondition() {
//        String impossibleShotValue = "100";
//        allHits.put(currentSocket, impossibleShotValue);
//        playerTracker.switchPlayers();
//        allHits.put(currentSocket, impossibleShotValue);
//        playerTracker.switchPlayers();
//
//    }

    public void checkShot() {
        String hit = messageReceiver.receiveMessage(currentReader);

        if(!(allHits.containsEntry(currentSocket, hit))) {
            allHits.put(currentSocket, hit);
            Integer toMark = Integer.parseInt(hit);
            Fleet fleet = playerTracker.getCurrentFleet();
            HitChecker hitChecker = new HitChecker(fleet);
            ShotState shotState = hitChecker.checkShot(toMark);
            showInfoAboutCurrentShot(hit, shotState, roundCounter);
            playerTracker.sendMessageToCurrentPlayer(shotState.toString());
            if (!(shotState == ShotState.HIT)) {
                playerTracker.switchPlayers();
            }
            roundCounter++;
        }
    }


//    private void addShootToList(String hit) {
//        List<String> playerShots = allHits.get(currentSocket);
//        playerShots.add(hit);
//    }
//
//    private boolean isShootAlreadyDone(String shoot) {
//        List<String> playerShots = allHits.get(currentSocket);
//        return playerShots.contains(shoot);
//    }


    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerTracker.currentPlayerName(), hit, shotState));
    }


}
