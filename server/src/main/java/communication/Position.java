package communication;

class Position {

    private final int index;
    private final int column;
    private final int row;

    Position(int index, int column, int row){
        this.index = index;
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
