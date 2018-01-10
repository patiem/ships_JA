package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class YouLostEvent extends Event {
  public static final EventType<YouLostEvent> LOST =
      new EventType<>(Event.ANY, "LOST");

  public YouLostEvent() {
    super(LOST);
  }
}
