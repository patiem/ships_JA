package connection;

import connection.OutputChannelDispatcher;
import connection.OutputSelector;

public class SystemErr implements OutputSelector {

  OutputChannelDispatcher outputChannelDispatcher;

  public SystemErr(OutputChannelDispatcher outputChannelDispatcher) {
    this.outputChannelDispatcher = outputChannelDispatcher;
  }

  @Override
  public void printToDesiredOutput(String message) {
    System.err.println(message);
  }
}
