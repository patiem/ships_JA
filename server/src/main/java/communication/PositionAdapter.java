package communication;

public class PositionAdapter {



    public static Position createPositionFromTwoCoordinates(int x, int y) {
        int index = x*y;
        return new Position(index);
    }
}
