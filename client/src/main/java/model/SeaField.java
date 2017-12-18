package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SeaField extends Rectangle implements Field {

    SimpleBooleanProperty isMarkedAsMast;
    SimpleBooleanProperty isMarkedAsBound;

    public SeaField(int i, int n) {
        super(i, n, 30, 30);
        setFill(Color.AZURE);
        setStroke(Color.GRAY);
        isMarkedAsMast = new SimpleBooleanProperty(this, "mast", false);
        isMarkedAsBound = new SimpleBooleanProperty(this, "bound", false);
    }

    public void hit() {
        setFill(Color.RED);
        System.out.println(getX());
        System.out.println(getY());
    }

    public void reset() {
        setFill(Color.AZURE);
        setStroke(Color.GRAY);
    }

    public void marked() {
        setFill(Color.GREEN);
        setStroke(Color.GRAY);
    }

    public void missed() {
        setFill(Color.BLACK);
    }

    public void makeToClick() {
        setFill(Color.BLANCHEDALMOND);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, makeReadyToClick);
    }

    public Integer calculateListPosition() {
        return (int) (getX() + getY() * 10);
    }

    public boolean isIsMarkedAsMast() {
        return isMarkedAsMast.get();
    }

    public SimpleBooleanProperty isMarkedAsMastProperty() {
        return isMarkedAsMast;
    }

    public void setIsMarkedAsMast(boolean isMarkedAsMast) {
        this.isMarkedAsMast.set(isMarkedAsMast);
    }

    public boolean isMarkedAsBound() {
        return isMarkedAsBound.get();
    }

    public SimpleBooleanProperty isMarkedAsBoundProperty() {
        return isMarkedAsBound;
    }

    public void setIsMarkedAsBound(boolean isMarkedAsBound) {
        this.isMarkedAsBound.set(isMarkedAsBound);
    }

    EventHandler<MouseEvent> makeReadyToClick = event -> {
                SeaField field = (SeaField) event.getSource();
                System.out.println(field.getX());
                System.out.println(field.getY());
                setIsMarkedAsMast(true);
            };
}