package engine;

import java.io.IOException;

public interface GameRunnerState {
    GameRunnerState runFixed() throws IOException;

    default boolean isGameRunning() {
        return true;
    }
}
