package converter.dto.converter;

import dto.TranscriptDTO;
import persistence.transcript.model.Transcript;

import java.util.List;
import java.util.stream.Collectors;

public class TranscriptConverter {

    private TranscriptConverter() {
    }

    public static TranscriptDTO convert(Transcript transcript) {
        return TranscriptDTO.create(transcript.getMessage(), transcript.getDate());
    }

    public static List<TranscriptDTO> convert(List<Transcript> transcript) {
        return transcript.stream().map(TranscriptConverter::convert).collect(Collectors.toList());
    }
}
