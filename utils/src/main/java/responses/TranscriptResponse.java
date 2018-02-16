package responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.TranscriptDTO;

import java.util.List;

public class TranscriptResponse implements Response {
    private List<TranscriptDTO> transcriptDTOs;

    @JsonCreator
    public TranscriptResponse(@JsonProperty("transcriptDTOs") List<TranscriptDTO> transcriptDTOs) {
        this.transcriptDTOs = transcriptDTOs;
    }

    private ResponseHeader header = ResponseHeader.TRANSCRIPT;

    @JsonProperty("header")
    @Override
    public ResponseHeader getHeader() {
        return header;
    }

    @Override
    public List<TranscriptDTO> getTranscriptDTOs() {
        return transcriptDTOs;
    }
}
