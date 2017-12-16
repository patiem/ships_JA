package communication;

class PositionAdapter {
    private static final int NUMBER_OF_COLUMNS = 10;

    static Position createPositionFromCoordinates(int column, int row) {
        int index = row * NUMBER_OF_COLUMNS + column;
        return new Position(index, column, row);
    }

    static Position createPositionFromIndex(int providedIndex) {
        int column = providedIndex % NUMBER_OF_COLUMNS;
        int row = providedIndex / NUMBER_OF_COLUMNS;

        return new Position(providedIndex, column, row);
    }
}
