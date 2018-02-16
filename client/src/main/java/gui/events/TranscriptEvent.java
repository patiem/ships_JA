package gui.events;

import javafx.event.Event;
import javafx.event.EventType;

public class TranscriptEvent extends Event {
    public static final EventType<TranscriptEvent> TRANSCRIPT =
        new EventType<>(Event.ANY, "Transcript");

    public TranscriptEvent() {
        super(TRANSCRIPT);
    }
}
