package fleet;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PositionValidatorTest {

    @DataProvider(name = "incorrect positions")
    public Object[] incorrectPositions() {
        return new Object[]{-5, -3, -224, 100, 259, 1000};
    }

    @Test(dataProvider = "incorrect positions")
    public void givenIncorrectPositionWhenIsValidThenReturnFalse(int position) {
        PositionValidator validator = new PositionValidator(new CustomFleet());
        assertFalse(validator.isValid(position));
    }

    @DataProvider(name = "correct positions")
    public Object[] correctPositions() {
        return new Object[]{5, 3, 24, 99, 89, 75};
    }

    @Test(dataProvider = "correct positions")
    public void givenCorrectPositionWhenIsValidThenReturnTrue(int position) {
        PositionValidator validator = new PositionValidator(new CustomFleet());
        assertTrue(validator.isValid(position));
    }

    @Test
    public void givenOccupiedIndexPositionWhenIsValidReturnFalse() {
        CustomFleet fleet = new CustomFleet();
        PositionValidator positionValidator = new PositionValidator(fleet);
        int position = 0;

        assertThat(positionValidator.isValid(position)).isTrue();

        fleet.addShip(new OneMastShip(Arrays.asList(position)));

        assertThat(positionValidator.isValid(position)).isFalse();
    }
}