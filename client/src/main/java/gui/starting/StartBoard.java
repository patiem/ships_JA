package gui.starting;

import connection.Client;
import gui.building.FleetDropController;
import gui.playing.PlayBoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MessageProcessor;
import model.Position;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * It represtents the playboard.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class StartBoard extends Application {

  private static final String START_BOARD_URL = "/fxmls/startBoard.fxml";
  private static final String BUILD_BOARD_URL = "/fxmls/buildBoardAllShips.fxml";
  private static final String PLAY_BOARD_URL = "/fxmls/playBoardEmpty.fxml";
  private static final Logger LOGGER = Logger.getLogger(StartBoard.class.getName());


  private final Client client = new Client();
  private MessageProcessor processor;
  private Stage stage;
  private AnchorPane buildBoard;
  private Group playRoot;
  private FleetDropController fleetDropController;

  public static void run(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    stage.setResizable(false);

    Group startRoot = new Group();
    Group buildRoot = new Group();
    playRoot = new Group();

    final double sceneWidth = 800;
    final double sceneHeight = 600;
    final Scene startScene = new Scene(startRoot, sceneWidth, sceneHeight);
    final Scene buildScene = new Scene(buildRoot, sceneWidth, sceneHeight);
    final Scene playScene = new Scene(playRoot, sceneWidth, sceneHeight);

    processor = new MessageProcessor();

    createStartBoard(startRoot, buildScene);
    createBuildBoard(buildRoot, playScene, processor);

    stage.setTitle("FXML Welcome");
    stage.setScene(startScene);
    stage.show();
  }

  private void createStartBoard(Group startRoot, Scene buildScene) throws IOException {
    StartBoardController startBoardController = new StartBoardController(client);
    FXMLLoader startLoader = new FXMLLoader(getClass().getResource(START_BOARD_URL));
    startLoader.setController(startBoardController);
    AnchorPane startingBoard = startLoader.load();
    startRoot.getChildren().addAll(startingBoard);
    addNextButtonToStartBoard(buildScene, startingBoard);
  }

  private void createBuildBoard(Group buildRoot, Scene playScene, MessageProcessor reactor) throws IOException {
    FXMLLoader buildLoader = new FXMLLoader(getClass().getResource(BUILD_BOARD_URL));
    fleetDropController = new FleetDropController(client);
    buildLoader.setController(fleetDropController);
    buildBoard = buildLoader.load();
    buildRoot.getChildren().addAll(buildBoard);
    addNextButtonToBuildBoard(playScene, buildBoard);
  }

  private void createPlayBoard(Group playRoot, MessageProcessor processor, List<Position> positions) throws IOException {
    FXMLLoader playLoader = new FXMLLoader(getClass().getResource(PLAY_BOARD_URL));
    PlayBoardController playBoardController = new PlayBoardController(client, processor, positions);
    playLoader.setController(playBoardController);
    AnchorPane playBoard = playLoader.load();
    playRoot.getChildren().addAll(playBoard);
    processor.putObserverTextFieldForConnection((TextField) playRoot.lookup("#winning"));
  }

  private void addNextButtonToStartBoard(Scene buildScene, AnchorPane startBoard) {
    Button connectButton = (Button) startBoard.lookup("#connectButton");
    connectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      String userName = ((TextField) startBoard.lookup("#userName")).getText();
      ((TextField) buildBoard.lookup("#userName")).setText(userName);
      stage.setScene(buildScene);
    });
  }

  private void addNextButtonToBuildBoard(Scene playScene, AnchorPane buildBoard) {
    Button buttonNext = (Button) buildBoard.lookup("#nextButton");
    buttonNext.addEventHandler(ConnectEvent.CONNECT, event -> {
      stage.setScene(playScene);
      try {
        createPlayBoard(playRoot, processor, fleetDropController.listOfMasts());
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, e.getMessage());
      }
    });
  }
}
