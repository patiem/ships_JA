package engine;

public class Game {

    private final Turn turn;

    public Game(Turn turn) {
        this.turn = turn;
    }

    public void runGame() {
        while (turn.gameState == GameState.ACTIVE) {
            turn.checkShot();
        }
    }
}
