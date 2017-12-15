package communication;

class PositionAdapter {

    static Position createPositionFromCoordinates(int column, int row) {
        int index = row * 10 + column;
        return new Position(index);
    }

    static Position createPositionFromIndex(int providedIndex) {
        int column = providedIndex % 10;
        int row = providedIndex / 10;

        return new Position(column, row);
    }
}
