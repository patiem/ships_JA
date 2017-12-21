package engine;

public class GameRunner {

    private final Round round;

    public GameRunner(Round round) {
        this.round = round;
    }

    public void runGame() {
        while (round.gameState == GameState.ACTIVE) {
            round.checkShot();
        }
    }
}
