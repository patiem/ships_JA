package responses;

import actions.MissedAction;

public class MissedResponse extends Response {

  public MissedResponse(){
    performAction = new MissedAction();
  }

  private ResponseHeader header = ResponseHeader.MISSED;

  @Override
  public ResponseHeader getHeader() {
    return header;
  }
}
