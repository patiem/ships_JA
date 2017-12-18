package model;

import java.util.Objects;

public class Position {

    Integer x;
    Integer y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    static Position up(Position position) {
        return new Position(position.x, position.y -1);
    }

    static Position down(Position position) {
        return new Position(position.x, position.y +1);
    }

    static Position right(Position position) {
        return new Position(position.x + 1, position.y);
    }

    static Position left(Position position) {
        return new Position(position.x - 1, position.y);
    }
}
