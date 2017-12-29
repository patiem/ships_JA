package engine;

class Shot {
  private Integer shotPosition;

  Shot(Integer shotPosition) {
    this.shotPosition = shotPosition;
  }

  Integer asInteger() {
    return shotPosition;
  }
}
