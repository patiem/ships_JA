package engine;

/**
 * It stores information on each shot.
 *
 * @author Bartosz Pieczara/Emilia Ciastek
 * @version 1.5
 */

class Shot {

    private Integer shotPosition;

    Shot(Integer shotPosition) {
      this.shotPosition = shotPosition;
    }

    Integer asInteger() {
      return shotPosition;
    }
  }

