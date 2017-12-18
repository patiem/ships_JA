package model;

public interface Field {
    
    Integer SIZE = 30;

    int getColumn();
    int getRow();

    default Position position() {
        return new Position(getColumn(), getRow());
    }
}