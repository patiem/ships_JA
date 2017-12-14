package fleet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class FleetTest {

    @Test
    public void givenEmptyFleetWhenGetFleetSizeThenReturn0() {
        Fleet fleet = new Fleet();
        int expectedFleetSize = 0;

        assertEquals(fleet.getSize(), expectedFleetSize);
    }

    @Test
    public void whenAddShipAndGetFleetSizeThenSizeIsIncremented() {
        Fleet fleet = new Fleet();
        int initialFleetSize = fleet.getSize();
        int expectedFleetSize = initialFleetSize + 1;
        Ship ship = new OneMastShip(Arrays.asList(0));

        fleet.addShip(ship);

        assertEquals(fleet.getSize(), expectedFleetSize);
    }

    @DataProvider(name = "ships to be added to fleet")
    private Object[] shipsToBeAdded() {
        return new Object[]{
                generateOneMastShip(),
                generateTwoMastShip(),
                generateOneMastShip()
        };
    }

    @Test(dataProvider = "ships to be added to fleet")
    public void whenAddShipsAndGetShipsThenReturnShips(Ship[] ships) {
        Fleet fleet = new Fleet();

        for (Ship ship : ships) {
            fleet.addShip(ship);
        }

        List<Ship> actualShips = fleet.getShips();
        int fleetSize = fleet.getSize();

        assertThat(fleetSize).isEqualTo(ships.length);
        assertThat(actualShips).containsExactly(ships);
    }

    @Test
    public void givenFleetWithTwoShipsWhenGetAllFleetPositionsThenReturnAllPositions() {
        Fleet fleet = new Fleet();
        fleet.addShip(new OneMastShip(Arrays.asList(0)));
        fleet.addShip(new TwoMastShip(Arrays.asList(1, 2)));

        assertThat(fleet.fleetPositions()).containsExactly(0, 1, 2);
    }

    private OneMastShip generateOneMastShip() {
        Random random = new Random();
        Integer[] positions = {random.nextInt(11)};

        return new OneMastShip(new ArrayList<>(Arrays.asList(positions)));
    }

    private TwoMastShip generateTwoMastShip() {
        Random random = new Random();
        Integer[] positions = {random.nextInt(11), random.nextInt(11)};

        return new TwoMastShip(new ArrayList<>(Arrays.asList(positions)));
    }
}