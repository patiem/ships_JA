package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mast extends Rectangle implements Field {

    private final int shipLength;
    private final Integer column;
    private final Integer row;

    public Mast(int column, int row, int shipLength) {
        super(column, row, SIZE, SIZE);
        this.column = column;
        this.row = row;
        this.shipLength = shipLength;
        setFill(Color.FUCHSIA);
        setStroke(Color.GREEN);
    }

    public int getShipLength() {
        return shipLength;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getRow() {
        return row;
    }
}
