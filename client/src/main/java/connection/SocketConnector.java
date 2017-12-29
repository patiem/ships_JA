package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnector implements Connector {

  private final Socket socket;

  private SocketConnector(Socket socket) {
    this.socket = socket;
  }

  public static SocketConnector from(String host, Integer port) throws IOException {
    return new SocketConnector(new Socket(host, port));
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
