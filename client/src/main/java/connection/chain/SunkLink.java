package connection.chain;

import common.model.ShipModel;
import gui.events.SunkShipEvent;
import gui.playing.DispatcherAdapter;
import responses.Response;
import responses.ResponseHeader;

public class SunkLink extends Chain {

  @Override
  public void analyzeResponse(Response response, DispatcherAdapter dispatcherAdapter) {
    if (response.getHeader() == ResponseHeader.SUNK) {
      ShipModel model = response.getSunkShip().orElseThrow(IllegalArgumentException::new);
      dispatcherAdapter.fireEvent(new SunkShipEvent(model.toString()));
    } else {
      nextInChain.analyzeResponse(response, dispatcherAdapter);
    }
  }
}
