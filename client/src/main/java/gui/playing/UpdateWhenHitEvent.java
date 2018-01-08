package gui.playing;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateEventWhenHit extends Event {
  public static final EventType<UpdateEventWhenHit> UPDATE =
      new EventType<>(Event.ANY, "UPDATE");
  private String message;

  public UpdateEventWhenHit(String message) {
    super(UPDATE);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
