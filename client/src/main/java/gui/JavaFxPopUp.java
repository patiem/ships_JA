package gui;

public class JavaFxPopUp implements OutputSelector {

    OutputChannelDispatcher outputChannelDispatcher;

    public JavaFxPopUp (OutputChannelDispatcher outputChannelDispatcher) {
        this.outputChannelDispatcher = outputChannelDispatcher;
    }

    @Override
    public void printToDesiredOutput(String message) {

    }
}
