package communication;

public class SystemErr implements OutputSelector {

  @Override
  public void writeMessage(String message) {
    System.err.println(message);
  }
}
