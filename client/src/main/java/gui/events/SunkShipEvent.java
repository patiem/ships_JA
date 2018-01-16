package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SunkShipEvent extends Event {
  public static final EventType<YouHitEvent> SUNK =
      new EventType<>(Event.ANY, "SUNK");

  public SunkShipEvent() {
    super(SUNK);
  }
  }
