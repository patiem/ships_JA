package model;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShipMapperTest {

  @Test
  public void givenMockedShipWhenMapToShipModelThenReturnCorrectShipModel() {
    int expectedNumberOfMasts = 2;
    List<Integer> expectedFields = Arrays.asList(0, 1);
    Ship shipToMap = mock(Ship.class);
    when(shipToMap.positionsOfAllMastInShipAsIntegers()).thenReturn(expectedFields);

    ShipMapper shipMapper = new ShipMapper();
    ShipModel actualResult = shipMapper.mapToModel(shipToMap);

    SoftAssertions soft = new SoftAssertions();
    soft.assertThat(actualResult.getFields().containsAll(expectedFields));
    soft.assertThat(actualResult.getNumberOfMasts()).isEqualTo(expectedNumberOfMasts);
    soft.assertAll();
  }

}