package engine;

public class Shot {
    private Integer shotPosition;

    public Shot(Integer shotPosition) {
        this.shotPosition = shotPosition;
    }

    public Integer asInteger() {
        return shotPosition;
    }
}
