package gui;

import javafx.scene.control.Alert;

public class JavaFxPopUp implements OutputSelector {

    OutputChannelDispatcher outputChannelDispatcher;

    public JavaFxPopUp (OutputChannelDispatcher outputChannelDispatcher) {
        this.outputChannelDispatcher = outputChannelDispatcher;
    }

    @Override
    public void printToDesiredOutput(String message) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
      alert.show();

    }
}
