package connection;

import java.io.IOException;
import java.io.PrintWriter;

public class MessageOut implements Sender {

  private final PrintWriter writer;

  private MessageOut(PrintWriter printer) {
    this.writer = printer;
  }

  public static MessageOut from(Connector connector) throws IOException {
    Boolean autoflush = true;
    return new MessageOut(new PrintWriter(connector.getOutStream(), autoflush));
  }

  @Override
  public void sendMessage(String message) {
    System.out.println(message);
    writer.println(message);
    writer.flush();
  }

}
