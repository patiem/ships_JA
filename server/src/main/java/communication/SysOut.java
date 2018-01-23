package communication;

public class SysOut implements OutputSelector {

  @Override
  public void writeMessage(String message) {
    System.out.println(message);

  }
}
