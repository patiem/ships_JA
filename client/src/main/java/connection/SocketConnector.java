package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 * It is responsible for establishing connections on the provided sockets.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class SocketConnector implements Connector {

  private final Socket socket;

  private SocketConnector(Socket socket) {
    this.socket = socket;
  }

  public static SocketConnector from(String host, Integer port) throws IOException {
    return new SocketConnector(new Socket(host, port));
  }

  /**
   * It obtains the OutputStream attached to a particular socket.
   *
   */
  @Override
  public OutputStream getOutStream() throws IOException {
    return socket.getOutputStream();
  }

  /**
   * It obtains the InputStream attached to a particular socket.
   *
   */
  @Override
  public InputStream getInStream() throws IOException {
    return socket.getInputStream();
  }
}
