package responses;

public class MissedResponse implements Response{

  private final String HEADER = "MISSED"; // TODO: check JSON


  @Override
  public String getHeader() {
    return HEADER;
  }
}
