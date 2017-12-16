package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    private final BufferedWriter writer;
    private String name;
    private Socket socket;
    private final Fleet fleet;

    public Player(String name, Socket socket) throws IOException {
        this.name = name;
        this.socket = socket;
        this.fleet = new HardcodedFleet();
        this.writer = new BufferedWriter(new PrintWriter(socket.getOutputStream(), false));
    }

    public String getName() {
        return name;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessageToPlayer(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
