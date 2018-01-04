package gui.starting;

import javafx.event.Event;
import javafx.event.EventType;
/**
 * It allows to create a connection based on the information received form the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class ConnectEvent extends Event {

  public static final EventType<ConnectEvent> CONNECT =
      new EventType<>(Event.ANY, "CON");

  private final String message;

  public ConnectEvent(String someMessage) {
    super(CONNECT);
    message = someMessage;
  }
}
