package engine;

import communication.MessageReceiver;
import model.Shot;

import java.io.BufferedReader;

/**
 * It reads data on the current shot.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class SocketShotReceiver implements ShotReceiver {
  private final MessageReceiver messageReceiver = new MessageReceiver();

  @Override
  public Shot readShot(BufferedReader currentReader) {

    String hit = messageReceiver.receiveMessage(currentReader);

    Integer shotPosition = Integer.parseInt(hit);

    return new Shot(shotPosition);
  }
}
