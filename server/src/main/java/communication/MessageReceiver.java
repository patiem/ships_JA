package communication;

import messages.ConnectionMessage;
import messages.ShotMessage;

import java.io.IOException;

public interface MessageReceiver {
    ConnectionMessage receiveConnectionMessage() throws IOException;

    ShotMessage receiveShotMessage() throws IOException;
}
