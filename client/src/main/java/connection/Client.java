package connection;

import java.io.*;
import java.net.Socket;

public class Client {

    Socket socket;
    BufferedWriter writer;
    BufferedReader reader;

    public void setup(String host, String port) {
        try {
            socket = new Socket(host, Integer.valueOf(port));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message)  {
        try {
            writer.write(message); //TODO: convert message to JSon
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        socket.close();
    }
}
