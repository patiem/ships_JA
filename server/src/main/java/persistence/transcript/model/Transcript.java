package persistence.transcript.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity which represents transcript in database
 */
@Entity(name = "transcript")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "message", length = 45)
    private String message;

    @Column(name = "date")
    private Date date;

    public Transcript(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    Transcript() {
    }
}
