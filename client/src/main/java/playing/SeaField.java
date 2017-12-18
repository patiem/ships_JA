package playing;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SeaField extends Rectangle implements Field {

    SeaField(int i, int n) {
        super(i, n, 30, 30);
        setFill(Color.AZURE);
        setStroke(Color.GRAY);
    }

    public void hit() {
        setFill(Color.RED);
    }

    public void marked() {
        setFill(Color.GREEN);
        System.out.println(getX());
        System.out.println(getY());
    }

    public Integer calculateListPosition() {
        return (int) (getX() + getY() * 10);
    }

    public void missed() {
        setFill(Color.BLACK);
    }
}
