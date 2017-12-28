package connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageIn implements Receiver {

  private final BufferedReader scanner;

  private MessageIn(BufferedReader scanner) {
    this.scanner = scanner;
  }

  static MessageIn from(Connector connector) throws IOException {
    return new MessageIn(new BufferedReader(new InputStreamReader(connector.getInStream(), "UTF-8")));
  }

  @Override
  public String readMessage() {
    try {
      String message = scanner.readLine();
      System.out.println(message);
      return message;
    } catch (IOException e) {
      e.printStackTrace(); //TODO: add exception handler
    }
    return "";
  }

  @Override
  public void distributeMessage() {

  }
}
