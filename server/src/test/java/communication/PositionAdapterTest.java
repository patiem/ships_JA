package communication;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PositionAdapterTest {

    @DataProvider(name = "coordinates provider")
    public static Object[][] coordinatesProvider() {
        return new Object[][]{
                {0, 0, 0},
                {1, 1, 11},
                {0, 5, 50},
                {3, 5, 53},
                {8, 6, 68},
                {9, 9, 99},
        };
    }

    @Test(dataProvider = "coordinates provider")
    public void shouldReturnPositionWithIndexFromProvidedCoordinates(int[] inputValues) {
        int column = inputValues[0];
        int row = inputValues[1];
        int expectedIndex = inputValues[2];

        Position createdPosition = PositionAdapter.createPositionFromCoordinates(column, row);

        int actualIndex = createdPosition.getIndex();

        assertEquals(actualIndex, expectedIndex);
    }

    @DataProvider(name = "indices provider")
    public static Object[][] indicesProvider() {
        return new Object[][]{
                {0, 0, 0},
                {11, 1, 1},
                {50, 0, 5},
                {53, 3, 5},
                {68, 8, 6},
                {99, 9, 9},
        };
    }


    @Test(dataProvider = "indices provider")
    public void shouldReturnPositionWithCoordinatesFromProvidedIndex(int[] inputValues) {
        int providedIndex = inputValues[0];
        int expectedColumn = inputValues[1];
        int expectedRow = inputValues[2];

        Position createdPosition = PositionAdapter.createPositionFromIndex(providedIndex);
        int actualColumn = createdPosition.getColumn();
        int actualRow = createdPosition.getRow();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualColumn).isEqualTo(expectedColumn);
        softly.assertThat(actualRow).isEqualTo(expectedRow);
        softly.assertAll();
    }

}