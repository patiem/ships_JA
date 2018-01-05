package gui.playing;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateEventWhenMissed extends Event {
  public static final EventType<UpdateEventWhenMissed> MISSED =
      new EventType<>(Event.ANY, "MISSED");
  private String message;

  public UpdateEventWhenMissed(String message) {
    super(MISSED);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
