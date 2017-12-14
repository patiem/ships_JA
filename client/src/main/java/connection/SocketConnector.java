package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnector implements Connector{

    Socket socket;

    public static SocketConnector from(String host, Integer port) throws IOException {
        return new SocketConnector(new Socket(host, port));
    }

    public SocketConnector(Socket socket) throws IOException {
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
