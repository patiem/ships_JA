package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.util.Date;

@Immutable
public class TranscriptDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private String message;
    @JsonProperty("date")
    private Date date;

    @JsonCreator
    public TranscriptDTO(@JsonProperty("message") String message, @JsonProperty("date") Date date) {
        this.message = message;
        this.date = date;
    }

    public static TranscriptDTO create(String message, Date date) {
        return new TranscriptDTO(message, date);
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
