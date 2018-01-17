package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SunkShipEvent extends Event {
  public static final EventType<SunkShipEvent> SUNK =
      new EventType<>(Event.ANY, "SUNK");

  public String getMessage() {
    return message;
  }

  private String message;

  public SunkShipEvent(String message) {
    super(SUNK);
    this.message = message;
  }
}
