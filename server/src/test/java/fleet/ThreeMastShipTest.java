package fleet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreeMastShipTest {

    @DataProvider(name = "mastsPositions")
    private Object[][] mastsPositions() {
        return new Object[][]{
                {0, 1, 2},
                {2, 3, 4},
                {4, 5, 6}
        };
    }

    @Test(dataProvider = "mastsPositions")
    public void whenGetFieldsThenReturnListWithPositions(Integer[] positions) {
        List<Integer> expectedFields = new ArrayList<>(Arrays.asList(positions));
        int expectedNumberOfMasts = 3;

        Ship ship = new ThreeMastShip(expectedFields);
        List<Integer> actualFields = ship.getFields();

        assertThat(ship.getNumberOfMasts()).isEqualTo(expectedNumberOfMasts);
        assertThat(actualFields).containsAll(expectedFields);
    }

}