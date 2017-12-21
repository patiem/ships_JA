package engine;

import communication.PlayerTracker;
import fleet.Fleet;

public class GameRunner {

    private final Round round;
    private PlayerTracker playerTracker;
    private ShotReceiver shotReceiver = new SocketShotReceiver();
    private GameState gameState = GameState.ACTIVE;


    public GameRunner(Round round, PlayerTracker playerTracker) {
        this.round = round;
        this.playerTracker = playerTracker;
    }

    public void runGame() {
        while (gameState == GameState.ACTIVE) {
            Shot shot = shotReceiver.readShot(playerTracker.getCurrentReader());
            Fleet fleetUnderFire = playerTracker.getFleetUnderFire();
            ShotResult result = round.makeShot(fleetUnderFire, shot);



        }
    }
}
