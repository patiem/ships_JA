package communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.JsonParserAdapter;
import messages.ConnectionMessage;
import messages.ShotMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It receives messages from the players.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class MessageReceiver {
  private static final Logger LOGGER = Logger.getLogger(MessageReceiver.class.getName());

  public String receiveMessage(final BufferedReader bufferedReader) {
    try {
      return bufferedReader.readLine();
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
    return "";
  }

  public String receiveMessage(final Socket socket) throws IOException {
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

    return reader.readLine();
  }

  public ConnectionMessage receiveConnectionMessage(final Socket socket) throws IOException {
    String jsonMessage = receiveMessage(socket);
    JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();

    return jsonParserAdapter.parse(
        jsonMessage, ConnectionMessage.class, new ObjectMapper());
  }

  public ShotMessage receiveShotMessage(final Socket socket) throws IOException {
    String jsonMessage = receiveMessage(socket);
    JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();

    return jsonParserAdapter.parse(
        jsonMessage, ShotMessage.class, new ObjectMapper());
  }
}
