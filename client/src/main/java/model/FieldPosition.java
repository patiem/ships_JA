package model;

public class FieldPosition {

    private final Integer positionX;
    private final Integer positionY;

    public FieldPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getX() {
        return positionX;
    }

    public Integer getY() {
        return positionY;
    }
}
