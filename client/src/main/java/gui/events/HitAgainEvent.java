package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class HitAgainEvent extends Event {
  public static final EventType<HitAgainEvent> HIT_AGAIN =
      new EventType<>(Event.ANY, "HIT_AGAIN");

  public String getMessage() {
    return message;
  }

  private String message;

  public HitAgainEvent(String message) {
    super(HIT_AGAIN);
    this.message = message;
  }
}
