package gui.starting;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * It allows to create a connection based on the information received form the server.
 *
 * @version 1.5
 */
public class ConnectEvent extends Event {

  public static final EventType<ConnectEvent> CONNECT =
      new EventType<>(Event.ANY, "CON");

  public ConnectEvent() {
    super(CONNECT);
  }
}
