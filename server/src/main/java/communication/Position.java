package communication;

class Position {

    private int index;
    private int column;
    private int row;

    Position(int index) {
        this.index = index;
    }

    Position(int column, int row) {
        this.column = column;
        this.row = row;
    }


    int getIndex() {
        return index;
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }
}
