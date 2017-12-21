package engine;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import communication.MessageReceiver;
import communication.PlayerTracker;
import fleet.Fleet;

import java.net.Socket;

public class Round {

    private int roundCounter = 0;
    private final MessageReceiver messageReceiver = new MessageReceiver();
    private final SetMultimap<Socket, String> allHits = MultimapBuilder.hashKeys().hashSetValues(50).build();
    private final PlayerTracker playerTracker;
    private Referee referee = new Referee();
    GameState gameState = GameState.ACTIVE;

    public Round(PlayerTracker playerTracker) {
        this.playerTracker = playerTracker;
    }

    void checkShot() {

        ShotReceiver shotReceiver = new SocketShotReceiver();
        Shot shot = shotReceiver.readShot(playerTracker.getCurrentReader());
        Integer shotPosition = shot.asInteger();


        String messageToSend;


        Fleet fleet = playerTracker.getFleetUnderFire();
        HitChecker hitChecker = new HitChecker(fleet);
        ShotState shotState = hitChecker.checkShot(shotPosition);
        showInfoAboutCurrentShot(shotPosition, shotState, roundCounter);

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

    private void showInfoAboutCurrentShot(Integer hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerTracker.currentPlayerName(), hit, shotState));
    }
}
