package persistence.transcript.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity which represents transcript in database
 */
@Entity(name = "transcript")
public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "message", length = 60)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public Transcript(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    Transcript() {
    }
}