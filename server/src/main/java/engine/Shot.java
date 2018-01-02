package engine;

/**
 * It stores information on each shot.
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */
public class Shot {
  private Integer shotPosition;

  public Shot(Integer shotPosition) {
    this.shotPosition = shotPosition;
  }

  public Integer asInteger() {
    return shotPosition;
  }
}
