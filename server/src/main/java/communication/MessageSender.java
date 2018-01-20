package communication;

import responses.Response;

import java.io.IOException;

public interface MessageSender {

    void sendMessageToPlayer(PlayerClient playerClient, String messageToSend) throws IOException;

    void sendResponse(Response responseToSend, PlayerClient player);
}
