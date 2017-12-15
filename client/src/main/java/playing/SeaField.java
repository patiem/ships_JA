package playing;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SeaField extends Rectangle implements Field {

    SeaField(int i, int n) {
        super(i, n, 30, 30);
        setFill(Color.AZURE);
        setStroke(Color.GRAY);
    }

    void hit() {
        setFill(Color.RED);
        System.out.println(getX());
        System.out.println(getY());
    }
}
