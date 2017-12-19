package fleet;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class HardcodedFleetTest {

    @Test
    public void whenGetFleetPositionsThenReturnHardcodedCollection() {
        HardcodedFleet hardcodedFleet = new HardcodedFleet();

        Integer[] expectedPositions = {0, 1, 2, 3, 17, 18, 19, 34, 40, 41, 44, 47, 54, 57, 61, 77, 78, 82, 85, 99};

        assertThat(hardcodedFleet.getFleetPositions()).containsExactlyInAnyOrder(expectedPositions);
    }

    @Test
    public void givenFleetWhenHitThenPositionAddedToSet(){
        HardcodedFleet hardcodedFleet = new HardcodedFleet();
        int position = 0;
        hardcodedFleet.hit(position);

        assertThat(hardcodedFleet.getHitFields()).containsExactlyInAnyOrder(position);
    }

    @Test
    public void givenSunkFleetWhenIsSunkThenTrue(){
        HardcodedFleet sunkFleet = new HardcodedFleet();
        List<Integer> hitsSequence = sunkFleet.getFleetPositions();
        Collections.shuffle(hitsSequence);

        hitsSequence.forEach(sunkFleet::hit);
        boolean actualState = sunkFleet.isSunk();

        assertTrue(actualState);
    }

}