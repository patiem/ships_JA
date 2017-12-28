package starting;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class ConnectEvent extends Event {

  public static final EventType<ConnectEvent> CONNECT =
      new EventType<>(Event.ANY, "CON");

  String message;

  public ConnectEvent(String s) {
    super(CONNECT);
    message = s;
  }

  public ConnectEvent(Object source, EventTarget target) {
    super(source, target, CONNECT);
  }
}
