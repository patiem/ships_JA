package model;

public class PossiblePosition {

  private final Mast mast;
  private final Sea sea;

  public PossiblePosition(Mast mast, Sea sea) {
    this.mast = mast;
    this.sea = sea;
  }

  void showPossibleMastPosition() {
    final int leftBoundary = 1;
    final int upperBoundary = 1;
    final int rightBoundary = 8;
    final int bottomBoundary = 8;
    if (mast.getRow() >= leftBoundary) {
      sea.getSeaFieldByPosition(Position.up(mast.position())).makeToClick();
    }
    if (mast.getRow() <= rightBoundary) {
      sea.getSeaFieldByPosition(Position.down(mast.position())).makeToClick();
    }
    if (mast.getColumn() <= bottomBoundary) {
      sea.getSeaFieldByPosition(Position.right(mast.position())).makeToClick();
    }
    if (mast.getColumn() >= upperBoundary) {
      sea.getSeaFieldByPosition(Position.left(mast.position())).makeToClick();
    }
  }
}
