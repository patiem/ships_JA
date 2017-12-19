package starting;

import building.FleetDropController;
import connection.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import playing.PlayBoardController;
import java.io.IOException;

public class StartBoard extends Application {

    private final double SCENE_WIDTH = 800;
    private final double SCENE_HEIGHT = 600;
    private final String START_BOARD_URL = "/fxmls/startBoard.fxml";
    private final String PLAY_BOARD_URL = "/fxmls/playBoardEmpty.fxml";
    private final String BUILD_BOARD_URL = "/fxmls/buildBoardAllShips.fxml";


    private Client client = new Client();
    private Stage stage;
    private AnchorPane playBoard;
    private AnchorPane buildBoard;
    private AnchorPane startBoard;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);

        Group startRoot = new Group();
        Group buildRoot = new Group();
        Group playRoot = new Group();

        Scene startScene = new Scene(startRoot, SCENE_WIDTH, SCENE_HEIGHT);
        Scene buildScene = new Scene(buildRoot, SCENE_WIDTH, SCENE_HEIGHT);
        Scene playScene = new Scene(playRoot, SCENE_WIDTH, SCENE_HEIGHT);

        createStartBoard(startRoot, buildScene);
        createBuildBoard(buildRoot, playScene);
        createPlayBoard(playRoot);

        stage.setTitle("FXML Welcome");
        stage.setScene(startScene);
        stage.show();
    }

    private void createStartBoard(Group startRoot, Scene buildScene) throws IOException {
        StartBoardController startBoardController = new StartBoardController(client);
        FXMLLoader startLoader = new FXMLLoader(getClass().getResource(START_BOARD_URL));
        startLoader.setController(startBoardController);
        startBoard = startLoader.load();
        startRoot.getChildren().addAll(startBoard);
        addNextButtonToStartBoard(buildScene, startBoard);
    }

    private void createBuildBoard(Group buildRoot, Scene playScene) throws IOException {
        FXMLLoader buildLoader = new FXMLLoader(getClass().getResource(BUILD_BOARD_URL));
        FleetDropController fleetDropController = new FleetDropController(client);
        buildLoader.setController(fleetDropController);
        buildBoard = buildLoader.load();
        buildRoot.getChildren().addAll(buildBoard);
        addNextButtonToBuildBoard(playScene, buildBoard);
    }

    private void createPlayBoard(Group playRoot) throws IOException {
        FXMLLoader playLoader = new FXMLLoader(getClass().getResource(PLAY_BOARD_URL));
        PlayBoardController playBoardController = new PlayBoardController(client);
        playLoader.setController(playBoardController);
        playBoard = playLoader.load();
        playRoot.getChildren().addAll(playBoard);
    }

//    private void addNextButtonToStartBoard(Scene playScene, AnchorPane startBoard) {
//        Button buttonNext = new Button("Next");
//        buttonNext.setVisible(false);
//        buttonNext.addEventHandler(ConnectEvent.CONNECT, event -> {
//            String userName = ((TextField)startBoard.lookup("#userName")).getText();
//            ((TextField)playBoard.lookup("#userName")).setText(userName);
//            stage.setScene(playScene);
//        });
//        VBox connectPanel = (VBox) startBoard.lookup("#connectPanel");
//        connectPanel.getChildren().add(buttonNext);
//        client.putObserverForConnection(buttonNext);
//    }

    private void addNextButtonToStartBoard(Scene buildScene, AnchorPane startBoard) {
        Button next = (Button) startBoard.lookup("#connectButton");
        next.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String userName = ((TextField) startBoard.lookup("#userName")).getText();
//            ((TextField) buildBoard.lookup("#userName")).setText(userName);
            stage.setScene(buildScene);
        });
    }

    private void addNextButtonToBuildBoard(Scene playScene, AnchorPane buildBoard) {
        Button buttonNext = new Button("Next");
        buttonNext.setDisable(true);
        buttonNext.addEventHandler(ConnectEvent.CONNECT, event -> {
            stage.setScene(playScene);
        });
        VBox connectPanel = (VBox) buildBoard.lookup("#connectPanel");
        connectPanel.getChildren().add(buttonNext);
        client.putObserverForConnection(buttonNext);
    }

    public static void run(String[] args) {
        Application.launch(args);
    }
}
