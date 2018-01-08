package events;

import javafx.event.Event;
import javafx.event.EventType;

public class YouWinEvent extends Event {
  public static final EventType<YouWinEvent> WIN =
      new EventType<>(Event.ANY, "WIN");

  public YouWinEvent() {
    super(WIN);
  }
}
