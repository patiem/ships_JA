package fleet;

import java.util.List;

interface Ship {
    int getNumberOfMasts();
    List<Integer> getFields(); //TODO: change Integer to Position
}
