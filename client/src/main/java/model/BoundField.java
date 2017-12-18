package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Field;


public class BoundField extends Rectangle implements Field {

    public BoundField(int i, int n) {
        super(i, n, 30, 30);
        setFill(Color.DARKGREY);
        setStroke(Color.GRAY);
    }


}
