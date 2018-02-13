package persistence.scores.model;

import javax.persistence.*;

/**
 * Entity which represents results of game in database
 */
@Entity(name = "results")
public class GameResults {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "winning_player", length = 45)
    private String winner;

    @Column(name = "losing_player", length = 45)
    private String loser;

    //todo think about unique identifier per game

}
