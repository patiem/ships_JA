package connection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class MessageOut implements Sender {

  private final PrintWriter writer;

  private MessageOut(PrintWriter printer) {
    this.writer = printer;
  }

  public static MessageOut from(Connector connector) throws IOException {
    return new MessageOut(new PrintWriter(new OutputStreamWriter(connector.getOutStream(), StandardCharsets.UTF_8)));
  }

  @Override
  public void sendMessage(String message) {
    writer.println(message);
    writer.flush();
  }

}
