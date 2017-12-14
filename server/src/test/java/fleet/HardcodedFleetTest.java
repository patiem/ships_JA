package fleet;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HardcodedFleetTest {

    @Test
    public void whenGetFleetPositionsThenReturnHardcodedCollection() {
        HardcodedFleet hardcodedFleet = new HardcodedFleet();

        Integer[] expectedPositions = {0, 1, 2, 3, 17, 18, 19, 34, 40, 41, 44, 47, 54, 57, 61, 77, 78, 82, 85, 99};

        assertThat(hardcodedFleet.fleetPositions()).containsExactlyInAnyOrder(expectedPositions);
    }

    @Test
    public void givenFleetWhenHitThenPositionAddedToSet(){
        HardcodedFleet hardcodedFleet = new HardcodedFleet();
        int position = 0;
        hardcodedFleet.hit(position);

        assertThat(hardcodedFleet.getHitFields()).containsExactlyInAnyOrder(position);
    }

}