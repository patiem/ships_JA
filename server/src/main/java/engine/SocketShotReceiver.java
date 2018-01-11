package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import communication.MessageReceiver;
import json.JsonParserAdapter;
import messages.ShotMessage;
import model.Shot;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * It reads data on the current shot.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class SocketShotReceiver implements ShotReceiver {
  private final MessageReceiver messageReceiver = new MessageReceiver();

  @Override
  public Shot readShot(BufferedReader currentReader) throws IOException {
    String receivedMessage = messageReceiver.receiveMessage(currentReader);
    JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();
    ShotMessage shotMessage = jsonParserAdapter.parse(
        receivedMessage, ShotMessage.class, new ObjectMapper());
    return shotMessage.getShot();
  }
}
