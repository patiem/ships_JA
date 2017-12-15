package playing;

public class FieldPosition {


    private final Integer x;
    private final Integer y;

    FieldPosition(int x, int y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
