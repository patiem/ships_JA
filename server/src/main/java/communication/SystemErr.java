package communication;

public class SystemErr implements Output {

  @Override
  public void transcript(String message) {
    System.err.println(message);
  }
}
