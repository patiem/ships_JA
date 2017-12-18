package model;

public class PossiblePosition {

    Mast mast;
    private Sea sea;

    public PossiblePosition(Mast mast, Sea sea) {
        this.mast = mast;
        this.sea = sea;
    }

    void showPossibleMastPosition() {
        System.out.println(mast.getX());
        System.out.println(mast.getY());
        if(mast.getY() >= 1 ) sea.getSeaFieldByPosition(Position.up(mast.position())).makeToClick();
        if(mast.getY() <= 8 ) sea.getSeaFieldByPosition(Position.down(mast.position())).makeToClick();
        if(mast.getX() <= 8 ) sea.getSeaFieldByPosition(Position.right(mast.position())).makeToClick();
        if(mast.getX() >= 1 ) sea.getSeaFieldByPosition(Position.left(mast.position())).makeToClick();
    }


}
