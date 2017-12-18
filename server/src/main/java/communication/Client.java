package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;

import java.io.*;

import java.net.Socket;

public class Client {

    private BufferedReader reader;
    private final BufferedWriter writer;
    private String name;
    private Socket socket;
    private final Fleet fleet;

//    public static Client playerBuilder(String name, Socket socket) throws IOException {
//            BufferedWriter writer = new BufferedWriter(new PrintWriter(socket.getOutputStream(), false));
//            return new Client(name, socket, writer);
//    }

     Client(String name, Socket socket, BufferedWriter writer, BufferedReader reader) {
        this.name = name;
        this.socket = socket;
        this.fleet = new HardcodedFleet();
        this.writer = writer;
        this.reader = reader;
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


    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }
}
