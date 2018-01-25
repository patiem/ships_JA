package connection.chain;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChainConfigFactoryTest {

  @Test
  public void whenConfigureThenReturnsInstanceOfHitLink() {
    // Act
    Chain firstChain = ChainConfigFactory.configureChainOfResponsibilities();
    // Assert
    assertThat(firstChain).isInstanceOf(PlayLink.class);
  }
}
