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

/**
 * It receives messages from the players.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class SocketMessageReceiver implements MessageReceiver {
    private final Socket socket;

    public SocketMessageReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public ConnectionMessage receiveConnectionMessage() throws IOException {
        String jsonMessage = receiveMessage();
        JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();

        return jsonParserAdapter.parse(
                jsonMessage, ConnectionMessage.class, new ObjectMapper());
    }

    @Override
    public ShotMessage receiveShotMessage() throws IOException {
        String jsonMessage = receiveMessage();
        JsonParserAdapter jsonParserAdapter = new JsonParserAdapter();

        return jsonParserAdapter.parse(
                jsonMessage, ShotMessage.class, new ObjectMapper());
    }

    private String receiveMessage() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        return reader.readLine();
    }
}
