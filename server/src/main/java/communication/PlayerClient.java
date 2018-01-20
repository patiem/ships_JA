package communication;

import fleet.Fleet;
import messages.ShotMessage;
import responses.PlayResponse;
import responses.Response;

import java.io.IOException;
import java.net.Socket;

/**
 * It hold a player's name, selected fleet and socket used for communication.
 *
 * @author Bartosz Pieczara / Emilia Ciastek
 * @version 1.5
 */
public class PlayerClient {

  private final Fleet fleet;
  private final String playerName;

  private MessageReceiver messageReceiver;
  private MessageSender messageSender;

  PlayerClient(String playerName, Fleet playerFleet, MessageReceiver messageReceiver, MessageSender messageSender) {
    this.playerName = playerName;
    this.fleet = playerFleet;
    this.messageReceiver = messageReceiver;
    this.messageSender = messageSender;
  }

  String getName() {
    return playerName;
  }

  Fleet getFleet() {
    return fleet;
  }

  public ShotMessage receiveShotMessage() throws IOException {
    return messageReceiver.receiveShotMessage();
  }

  public void sendResponse(Response response) {
    messageSender.sendResponse(response);
  }
}
