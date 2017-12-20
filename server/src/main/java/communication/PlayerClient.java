package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;

import java.io.*;

import java.net.Socket;

public class PlayerClient {

    private final BufferedReader reader;

    private final Socket socket;
    private final Fleet fleet;
    private String playerName;


    public PlayerClient(String playerName, Socket socket, BufferedReader reader, Fleet playerFleet) {
        this.playerName = playerName;

        this.socket = socket;
        this.fleet = playerFleet;
        this.reader = reader;
    }


    String getName() {
        return playerName;
    }

    Fleet getFleet() {
        return fleet;
    }

    Socket getSocket() {
        return socket;
    }

    public void sendMessageToPlayer(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new PrintWriter(socket.getOutputStream(), false));

            writer.write(message);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public BufferedReader getReader() {
        return reader;
    }

}
