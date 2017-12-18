package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

class Player {

    private final BufferedWriter writer;
    private String name;
    private Socket socket;
    private final Fleet fleet;

    public static Player playerBuilder(String name, Socket socket) throws IOException {
            BufferedWriter writer = new BufferedWriter(new PrintWriter(socket.getOutputStream(), false));
            return new Player(name, socket, writer);
    }

    private Player(String name, Socket socket, BufferedWriter writer) {
        this.name = name;
        this.socket = socket;
        this.fleet = new HardcodedFleet();
        this.writer = writer;
    }

    String getName() {
        return name;
    }

    Fleet getFleet() {
        return fleet;
    }

    Socket getSocket() {
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
