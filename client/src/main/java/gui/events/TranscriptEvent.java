package gui.events;

import dto.TranscriptDTO;
import javafx.event.Event;
import javafx.event.EventType;

import java.util.List;

public class TranscriptEvent extends Event {
    public static final EventType<TranscriptEvent> TRANSCRIPT =
        new EventType<>(Event.ANY, "Transcript");

    private List<TranscriptDTO> dtos;

    public TranscriptEvent(List<TranscriptDTO> dtos) {
        super(TRANSCRIPT);
        this.dtos = dtos;
    }

    public List<TranscriptDTO> getDtos() {
        return dtos;
    }
}
