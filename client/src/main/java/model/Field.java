package model;

public interface Field {

    double getX();
    double getY();

    default Position position() {
        return new Position((int) getX(), (int) getY());
    }


}