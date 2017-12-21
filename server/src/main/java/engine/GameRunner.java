package engine;

import communication.PlayerTracker;
import fleet.Fleet;
import org.apache.log4j.Logger;


public class GameRunner {

    private final Round round;
    private final PlayerTracker playerTracker;
    private final ShotReceiver shotReceiver = new SocketShotReceiver();
    private GameState gameState = GameState.ACTIVE;
    private final Referee referee = new Referee();
    private final Logger logger = Logger.getLogger(GameRunner.class);


    public GameRunner(Round round, PlayerTracker playerTracker) {
        this.round = round;
        this.playerTracker = playerTracker;
    }

    public void runGame() {
        while (gameState == GameState.ACTIVE) {
            Shot shot = shotReceiver.readShot(playerTracker.getCurrentReader());
            Fleet fleetUnderFire = playerTracker.getFleetUnderFire();
            ShotResult result = round.makeShot(fleetUnderFire, shot);
            gameState = referee.isVictory(fleetUnderFire);

            sendMessage(result);

            playerTracker.switchPlayers();
            logShotInfo(shot, result);
        }
    }

    private void sendMessage(ShotResult result) {
        String message = result.toString();

        if (gameState == GameState.WIN)
            message = gameState.toString();

        playerTracker.sendMessageToCurrentPlayer(message);
    }

    private void logShotInfo(Shot shot, ShotResult shotResult) {
        String logMessage = String.format("Player: %s, shot: position: %s, shotState: %s; gameState: %s", playerTracker.currentPlayerName(), shot.asInteger(), shotResult, gameState);
        logger.info(logMessage);
    }
}
