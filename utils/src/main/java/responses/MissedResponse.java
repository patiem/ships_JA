package responses;

public class MissedResponse extends Response {

  private ResponseHeader header = ResponseHeader.MISSED;

  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
