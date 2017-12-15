package communication;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PositionAdapterTest {



    @DataProvider (name = "coordinatesProvider")
    public static Object [][] coordinatesProvider() {



        return new Object[][]{
                {0,0,0},
                {1,1,11},
                {0,5,5},
                {3,5,35},
                {8,6,86},
                {9,9,99},
        };
    }

    @Test(dataProvider = "coordinatesProvider")
    public void givenTwoParametersFromGUIConvertToOneNumber() {

        Position providedPosition = PositionAdapter.createPositionFromTwoCoordinates(x, y);

        assertEquals(providedPosition.getIndex(), x * y);

    }


}