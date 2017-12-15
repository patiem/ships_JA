package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnector implements Connector{

    private static final String LOCALHOST = "localhost";
    private static final Integer PORT = 5000;

    private final Socket socket;

    public static SocketConnector from(String host, Integer port) throws IOException {
        return new SocketConnector(new Socket(host, port));
    }

    public static Connector fromLocalhost() throws IOException {
        return new SocketConnector(new Socket(LOCALHOST, PORT));
    }

    public SocketConnector(Socket socket) {
        this.socket = socket;
    }

    @Override
    public OutputStream getOutStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public InputStream getInStream() throws IOException {
        return socket.getInputStream();
    }
}
