package gui;

public class NoSelection implements OutputSelector{

  OutputChannelDispatcher outputChannelDispatcher;

  public NoSelection(OutputChannelDispatcher outputChannelDispatcher) {
    this.outputChannelDispatcher = outputChannelDispatcher;
  }

  @Override
  public void printToDesiredOutput(String message) {

  }
}
