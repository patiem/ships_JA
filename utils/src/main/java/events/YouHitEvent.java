package events;

import javafx.event.Event;
import javafx.event.EventType;

public class YouHitEvent extends Event {
  public static final EventType<YouHitEvent> HIT =
      new EventType<>(Event.ANY, "HIT");

  public YouHitEvent() {
    super(HIT);
  }
}
