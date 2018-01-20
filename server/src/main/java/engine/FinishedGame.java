package engine;

import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import responses.LossResponse;
import responses.WinResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class FinishedGame implements GameState {

    private static final Logger LOGGER = Logger.getLogger(FinishedGame.class.getName());

    private PlayerRegistry playerRegistry;
    private boolean isGameRunning = true;

    FinishedGame(PlayerRegistry playerRegistry) {
        this.playerRegistry = playerRegistry;
    }

    @Override
    public GameState run() throws IOException {
        PlayerClient winner = playerRegistry.getCurrentPlayer();
        PlayerClient looser = playerRegistry.getWaitingPlayer();

        winner.sendResponse(new WinResponse());
        looser.sendResponse(new LossResponse());
        String logMessage = String.format("Message has been send. Player %s won, player %s lost",
                winner, looser);

        LOGGER.info(logMessage);
        isGameRunning = false;
        return this;
    }

    @Override
    public boolean isGameRunning() {
        return isGameRunning;
    }
}
