package events;

import javafx.event.Event;
import javafx.event.EventType;


public class YourTurnEvent extends Event {
  public static final EventType<YourTurnEvent> TURN =
      new EventType<>(Event.ANY, "TURN");

  public YourTurnEvent() {
    super(TURN);
  }
}
