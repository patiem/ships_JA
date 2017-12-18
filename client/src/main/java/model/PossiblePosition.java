package model;

public class PossiblePosition {

    private final Mast mast;
    private final Sea sea;

    public PossiblePosition(Mast mast, Sea sea) {
        this.mast = mast;
        this.sea = sea;
    }

    void showPossibleMastPosition() {
        int leftBoundary = 1;
        int upperBoundary = 1;
        int rightBoundary = 8;
        int bottomBoundary = 8;
        if(mast.getRow() >= leftBoundary ) sea.getSeaFieldByPosition(Position.up(mast.position())).makeToClick();
        if(mast.getRow() <= rightBoundary ) sea.getSeaFieldByPosition(Position.down(mast.position())).makeToClick();
        if(mast.getColumn() <= bottomBoundary ) sea.getSeaFieldByPosition(Position.right(mast.position())).makeToClick();
        if(mast.getColumn() >= upperBoundary ) sea.getSeaFieldByPosition(Position.left(mast.position())).makeToClick();
    }
}
