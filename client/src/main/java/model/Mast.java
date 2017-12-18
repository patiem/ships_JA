package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Field;

public class Mast extends Rectangle implements Field {

    private int length;

    public Mast(int i, int n, int lenght) {
        super(i, n, 30, 30);
        this.length = lenght;
        setFill(Color.FUCHSIA);
        setStroke(Color.GREEN);
    }

    public int getLength() {
        return length;
    }
}
