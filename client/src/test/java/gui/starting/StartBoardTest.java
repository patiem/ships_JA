package gui.starting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@ExtendWith(ApplicationExtension.class)
class StartBoardTest {

  @BeforeEach
  void setUp() throws TimeoutException {
    FxToolkit.registerPrimaryStage();
    FxToolkit.setupApplication(StartBoard.class);
  }

  @Test
  void should_StartBoard_contain_button() {
    // expect:
    verifyThat("#connectButton", hasText("Next"));
  }

  @Test
  void should_show_build_board_when_nextButton_clicked(FxRobot robot) {
    // when:
    robot.clickOn("#connectButton");

    // then:
    verifyThat("#connectButton", hasText("Play"));
  }

  @Test
  void should_show_play_board_when_connectButton_clicked(FxRobot robot) {
    // when:
    robot.clickOn("#userName");
    robot.write("Player 1");
    robot.clickOn("#connectButton");
    robot.clickOn("#random");
    robot.clickOn("#connectButton");

    // then:
    verifyThat("#targetBoard", isVisible());
    verifyThat("#shipBoard", isVisible());
  }
}