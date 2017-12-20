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

    private final PlayerTracker playerTracker;
    private Socket currentSocket;
    private final GameState gameState;
    private final MessageReceiver messageReceiver;
    private int roundCounter = 0;
    private BufferedReader currentReader;
    private final SetMultimap<Socket, String> allHits;
    private final Referee referee = new Referee();


    public Game(PlayerTracker playerTracker, MessageReceiver messageReceiver) {
        this.playerTracker = playerTracker;
        this.allHits = MultimapBuilder.hashKeys().hashSetValues(50).build();
        gameState = GameState.ACTIVE;
        this.messageReceiver = messageReceiver;
    }

    public void runGame() {
        while (gameState == GameState.ACTIVE) {
            currentSocket = playerTracker.getCurrentSocket();
            currentReader = playerTracker.getCurrentPlayerClient().getReader();
            checkShot();
        }
    }

    private void checkShot() {
        String hit = messageReceiver.receiveMessage(currentReader);
        System.out.println(hit);

        if (!(allHits.containsEntry(currentSocket, hit))) {
            allHits.put(currentSocket, hit);
            Integer toMark = Integer.parseInt(hit);
            Fleet fleet = playerTracker.getCurrentFleet();
            HitChecker hitChecker = new HitChecker(fleet);
            ShotState shotState = hitChecker.checkShot(toMark);
            showInfoAboutCurrentShot(hit, shotState, roundCounter);
            playerTracker.sendMessageToCurrentPlayer(shotState.toString());
            if (referee.isVictory(fleet)) playerTracker.sendMessageToCurrentPlayer("WIN");

            if (!(shotState == ShotState.HIT)) {
                playerTracker.switchPlayers();
                playerTracker.sendMessageToCurrentPlayer("PLAY");
            }
            roundCounter++;
        }
    }

    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerTracker.currentPlayerName(), hit, shotState));
    }
}
