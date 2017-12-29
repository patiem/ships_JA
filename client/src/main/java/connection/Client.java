package connection;

import java.io.IOException;

public class Client {

  private static final Integer port = 5000;

  private Connector connector;
  private Sender out;
  private Receiver in;
  private String host;

  public void run() {
    try {
      connector = SocketConnector.from(host, port);
      in = MessageIn.from(connector);
      out = MessageOut.from(connector);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setup(String host) {
    this.host = host;
  }

  public void sendMessage(String message) {
    out.sendMessage(message);
  }


  public String getMessage() {
    return in.readMessage();
  }
}
