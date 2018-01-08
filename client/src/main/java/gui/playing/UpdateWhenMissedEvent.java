package gui.playing;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateWhenMissedEvent extends Event {
  public static final EventType<UpdateWhenMissedEvent> MISSED =
      new EventType<>(Event.ANY, "MISSED");
  private String message;

  public UpdateWhenMissedEvent(String message) {
    super(MISSED);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
