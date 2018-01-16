package model;

import gui.fields.ClickableField;
import gui.fields.Field;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PossiblePositionsTest {
  private Sea sea;

  @BeforeClass
  public void createSea() {
    sea = new Sea();
    IntStream.range(0, 99).forEach(i -> sea.addSeaField(new DummyClickableField(i)));
  }

  @Test
  public void shouldMakeClickableAllFieldsInProvidedList() {
    List<ClickableField> fieldsToMakeClickable = new ArrayList<>();
    ClickableField mockedFirstField = mock(ClickableField.class); //TODO: change mock to real object?
    ClickableField mockedSecondField = mock(ClickableField.class);
    fieldsToMakeClickable.add(mockedFirstField);
    fieldsToMakeClickable.add(mockedSecondField);

    PossiblePositions.makePositionClickable(fieldsToMakeClickable);

    verify(mockedFirstField).makeClickable();
    verify(mockedSecondField).makeClickable();
  }

  @DataProvider(name = "border masts")
  public Object[][] provideBorderMastAndExpectedPositions() {
    return new Object[][] {
        {new DummyMast(0), new Integer[] {1, 10}},
        {new DummyMast(9), new Integer[] {8, 19}},
        {new DummyMast(90), new Integer[] {80, 91}},
        {new DummyMast(99), new Integer[] {98, 89}},
        {new DummyMast(5), new Integer[] {4, 6, 15}},
        {new DummyMast(80), new Integer[] {81, 70, 90}},
        {new DummyMast(29), new Integer[] {28, 19, 39}},
        {new DummyMast(93), new Integer[] {83, 92, 94}}
    };
  }

  @Test(dataProvider = "border masts")
  public void shouldReturnPositionsAvailableForBorderMastPlacement(Field mast, Integer[] expectedPositions) {
    List<ClickableField> actualAvailableFields = PossiblePositions.findPositions(mast, sea);
    List<Integer> actualAvailablePositions = actualAvailableFields
        .stream()
        .map(Field::positionAsInteger)
        .collect(Collectors.toList());

    assertThat(actualAvailablePositions).containsExactlyInAnyOrder(expectedPositions);
  }

  @DataProvider(name = "masts")
  public Object[][] provideMastAndExpectedPositions() {
    return new Object[][] {
        {new DummyMast(11), new Integer[] {1, 10, 12, 21}},
        {new DummyMast(55), new Integer[] {45, 54, 56, 65}},
        {new DummyMast(78), new Integer[] {68, 77, 79, 88}},
        {new DummyMast(36), new Integer[] {26, 37, 35, 46}},
    };
  }

  @Test(dataProvider = "masts")
  public void shouldReturnPositionsAvailableForMastPlacement(Field mast, Integer[] expectedPositions) {
    List<ClickableField> actualAvailableFields = PossiblePositions.findPositions(mast, sea);
    List<Integer> actualAvailablePositions = actualAvailableFields
        .stream()
        .map(Field::positionAsInteger)
        .collect(Collectors.toList());

    assertThat(actualAvailablePositions).containsExactlyInAnyOrder(expectedPositions);
  }
}
