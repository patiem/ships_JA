package model;

import java.io.IOException;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "fieldsInside")
    public static Object[][] scenarios() throws IOException {

        int startColumn = 2;
        int endColumn = 7;
        int startRow = 2;
        int endRow = 7;
        int counter = 0;

        Object[][] fields = new Object[endRow * endColumn][];

        for(int column = startColumn; column <= endColumn; column++) {
            for(int row = startRow; row <= endRow; row++) {
                fields[counter++] =  new Object[]{column, row};
            }
        }
        return fields;
    }

}
