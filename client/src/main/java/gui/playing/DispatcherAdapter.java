package gui.playing;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Node;

public class DispatcherAdapter {
  private Node dispatcher;

  public DispatcherAdapter(Node dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void fireEvent(Event event) {
    Platform.runLater(() -> dispatcher.fireEvent(event));
  }
}
