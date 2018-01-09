package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateWhenMissedEvent extends Event {
  public static final EventType<UpdateWhenMissedEvent> UPDATE_MISSED =
      new EventType<>(Event.ANY, "UPDATE_MISSED");
  private String message;

  public UpdateWhenMissedEvent(String message) {
    super(UPDATE_MISSED);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
