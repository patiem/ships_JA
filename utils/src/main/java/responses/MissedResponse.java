package responses;

public class MissedResponse implements Response {

  private ResponseHeader header = ResponseHeader.MISSED;

  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
