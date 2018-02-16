package dto;

import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.util.Date;

@Immutable
public class TranscriptDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private Date date;

    private TranscriptDTO(String message, Date date) {
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
