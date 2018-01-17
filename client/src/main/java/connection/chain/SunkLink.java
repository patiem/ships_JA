package connection.chain;

import gui.events.SunkShipEvent;
import gui.events.UpdateWhenMissedEvent;
import gui.playing.DispatcherAdapter;
import model.ShipModel;
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
