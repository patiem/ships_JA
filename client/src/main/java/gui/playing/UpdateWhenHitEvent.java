package gui.playing;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateWhenHitEvent extends Event {
  public static final EventType<UpdateWhenHitEvent> UPDATE =
      new EventType<>(Event.ANY, "UPDATE");
  private String message;

  public UpdateWhenHitEvent(String message) {
    super(UPDATE);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
