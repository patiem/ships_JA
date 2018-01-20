package engine;

import java.io.IOException;

public interface GameState {
    GameState run() throws IOException;

    default boolean isGameRunning() {
        return true;
    }
}
