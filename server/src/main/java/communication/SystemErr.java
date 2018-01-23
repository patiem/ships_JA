package communication;

public class SystemErr implements Output {

  @Override
  public void writeMessage(String message) {
    System.err.println(message);
  }
}
