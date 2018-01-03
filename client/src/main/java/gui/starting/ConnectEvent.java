package gui.starting;

import javafx.event.Event;
import javafx.event.EventType;

public class ConnectEvent extends Event {

  public static final EventType<ConnectEvent> CONNECT =
      new EventType<>(Event.ANY, "CON");

  private final String message;

  public ConnectEvent(String someMessage) {
    super(CONNECT);
    message = someMessage;
  }
}
