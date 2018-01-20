package gui;

public class SysOut implements OutputSelector {

    OutputChannelDispatcher outputChannelDispatcher;

    public SysOut(OutputChannelDispatcher outputChannelDispatcher) {
        this.outputChannelDispatcher = outputChannelDispatcher;
    }

    @Override
    public void printToDesiredOutput(String message) {
      System.out.println(message);

    }
}
