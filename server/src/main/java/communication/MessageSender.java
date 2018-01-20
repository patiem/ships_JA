package communication;

import responses.Response;

public interface MessageSender {

    void sendResponse(Response responseToSend);
}
