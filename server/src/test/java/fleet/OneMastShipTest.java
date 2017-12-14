package fleet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OneMastShipTest {

    @DataProvider(name = "mastsPositions")
    private Object[][] mastsPositions(){
        return new Object[][] {
                {0},
                {2},
                {4}
        };
    }

    @Test (dataProvider = "mastsPositions")
    public void whenGetFieldsThenReturnListWithPositions(Integer[] positions) {
        List<Integer> expectedFields = new ArrayList<>(Arrays.asList(positions));
        int expectedNumberOfMasts = 1;

        Ship ship = new OneMastShip(expectedFields);
        List<Integer> actualFields = ship.getFields();

        assertThat(ship.getNumberOfMasts()).isEqualTo(expectedNumberOfMasts);
        assertThat(actualFields).containsAll(expectedFields);
    }
}
