package gui.playing;

import javafx.event.Event;
import javafx.event.EventType;

public class YouMissedEvent extends Event {
  public static final EventType<YouMissedEvent> WAIT =
      new EventType<>(javafx.event.Event.ANY, "WAIT");

  public YouMissedEvent() {
    super(WAIT);
  }
}
