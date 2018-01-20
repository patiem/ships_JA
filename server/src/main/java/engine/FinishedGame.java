package engine;

import communication.MessageSender;
import communication.PlayerClient;
import communication.PlayerRegistry;
import responses.LossResponse;
import responses.WinResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class FinishedGame implements GameRunnerState {

    private final MessageSender messageSender;
    private PlayerRegistry playerRegistry;
    private boolean isGameRunning = true;

    FinishedGame(PlayerRegistry playerRegistry, MessageSender messageSender) {
        this.playerRegistry = playerRegistry;
        this.messageSender = messageSender;
    }

    private void sendFinalResponse() {
        Logger logger = Logger.getLogger(FinishedGame.class.getName());

        PlayerClient winner = playerRegistry.getCurrentPlayer();
        PlayerClient looser = playerRegistry.getWaitingPlayer();

        messageSender.sendResponse(new WinResponse(), winner);
        messageSender.sendResponse(new LossResponse(), looser);
        String logMessage = String.format("Message has been send. Player %s won, player %s lost", winner, looser);

        logger.info(logMessage);
    }

    @Override
    public GameRunnerState runFixed() throws IOException {
        sendFinalResponse();
        isGameRunning = false;
        return this;
    }

    @Override
    public boolean isGameRunning() {
        return isGameRunning;
    }
}
