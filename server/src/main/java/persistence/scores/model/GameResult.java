package persistence.scores.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity which represents results of game in database
 */
@Entity(name = "results")
public class GameResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "winning_player", length = 45)
    private String winner;

    @Column(name = "losing_player", length = 45)
    private String loser;

    //todo think about unique identifier per game

}
