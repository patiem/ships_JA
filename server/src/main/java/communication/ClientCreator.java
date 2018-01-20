package communication;

import fleet.CustomFleet;
import fleet.Fleet;
import messages.ConnectionMessage;

import java.io.IOException;

public class ClientCreator {
    private final MessageReceiver messageReceiver;
    private final MessageSender messageSender;

    public ClientCreator(MessageReceiver messageReceiver, MessageSender messageSender) {
        this.messageReceiver = messageReceiver;
        this.messageSender = messageSender;
    }

    public PlayerClient createClient() throws IOException {
        ConnectionMessage connectionMessage = messageReceiver.receiveConnectionMessage();
        String playerName = connectionMessage.getName();
        Fleet playerFleet = new CustomFleet(connectionMessage.getFleetModel());
        return new PlayerClient(playerName, playerFleet, messageReceiver, messageSender);
    }
}
