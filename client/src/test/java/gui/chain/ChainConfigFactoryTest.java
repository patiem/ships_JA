package gui.chain;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ChainConfigFactoryTest {

  @Test
  public void whenCalledThenReturnsDesiredObject() {
    // Act
    Chain testObject = ChainConfigFactory.configureChainOfResponsibilities();
    // Assert
    assertTrue(testObject.getClass()==HitLink.class);
    assertFalse(testObject.getClass()==LostLink.class);
  }
}