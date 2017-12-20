package engine;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import communication.MessageReceiver;
import communication.PlayerTracker;
import fleet.Fleet;

import java.io.BufferedReader;
import java.net.Socket;

public class Turn {

    private int roundCounter = 0;
    private final MessageReceiver messageReceiver = new MessageReceiver();
    private final SetMultimap<Socket, String> allHits = MultimapBuilder.hashKeys().hashSetValues(50).build();
    private final PlayerTracker playerTracker;
    private Referee referee = new Referee();
    GameState gameState;

    public Turn(PlayerTracker playerTracker) {
        this.playerTracker = playerTracker;
    }

    void checkShot() {

        Socket currentSocket = playerTracker.getCurrentSocket();
        BufferedReader currentReader = playerTracker.getCurrentPlayerClient().getReader();

        String hit = messageReceiver.receiveMessage(currentReader);

        if (!(allHits.containsEntry(currentSocket, hit))) {
            String messageToSend;
            allHits.put(currentSocket, hit);
            Integer toMark = Integer.parseInt(hit);
            Fleet fleet = playerTracker.getCurrentFleet();
            HitChecker hitChecker = new HitChecker(fleet);
            ShotState shotState = hitChecker.checkShot(toMark);
            showInfoAboutCurrentShot(hit, shotState, roundCounter);

            if (referee.isVictory(fleet)) {
                messageToSend = "WIN";
                gameState = GameState.WIN;
            } else messageToSend = shotState.toString();

            playerTracker.sendMessageToCurrentPlayer(messageToSend);

            if (!(shotState == ShotState.HIT)) {
                playerTracker.switchPlayers();
            }
            roundCounter++;
        }
    }

    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerTracker.currentPlayerName(), hit, shotState));
    }
}
