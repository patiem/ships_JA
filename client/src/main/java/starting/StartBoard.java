package starting;

import building.FleetDropController;
import connection.Client;
import model.MessageReactor;
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


    private final Client client = new Client();
    private MessageReactor reactor;
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

        double SCENE_WIDTH = 800;
        double SCENE_HEIGHT = 600;
        Scene startScene = new Scene(startRoot, SCENE_WIDTH, SCENE_HEIGHT);
        Scene buildScene = new Scene(buildRoot, SCENE_WIDTH, SCENE_HEIGHT);
        Scene playScene = new Scene(playRoot, SCENE_WIDTH, SCENE_HEIGHT);

        reactor = new MessageReactor();

        createStartBoard(startRoot, buildScene);
        createBuildBoard(buildRoot, playScene, reactor);
        createPlayBoard(playRoot, reactor);

        stage.setTitle("FXML Welcome");
        stage.setScene(startScene);
        stage.show();
    }

    private void createStartBoard(Group startRoot, Scene buildScene) throws IOException {
        StartBoardController startBoardController = new StartBoardController(client);
        String START_BOARD_URL = "/fxmls/startBoard.fxml";
        FXMLLoader startLoader = new FXMLLoader(getClass().getResource(START_BOARD_URL));
        startLoader.setController(startBoardController);
        startBoard = startLoader.load();
        startRoot.getChildren().addAll(startBoard);
        addNextButtonToStartBoard(buildScene, startBoard);
    }

    private void createBuildBoard(Group buildRoot, Scene playScene, MessageReactor reactor) throws IOException {
        String BUILD_BOARD_URL = "/fxmls/buildBoardAllShips.fxml";
        FXMLLoader buildLoader = new FXMLLoader(getClass().getResource(BUILD_BOARD_URL));
        FleetDropController fleetDropController = new FleetDropController(client, reactor);
        buildLoader.setController(fleetDropController);
        buildBoard = buildLoader.load();
        buildRoot.getChildren().addAll(buildBoard);
        addNextButtonToBuildBoard(playScene, buildBoard);
    }

    private void createPlayBoard(Group playRoot, MessageReactor reactor) throws IOException {
        String PLAY_BOARD_URL = "/fxmls/playBoardEmpty.fxml";
        FXMLLoader playLoader = new FXMLLoader(getClass().getResource(PLAY_BOARD_URL));
        PlayBoardController playBoardController = new PlayBoardController(client, reactor);
        playLoader.setController(playBoardController);
        playBoard = playLoader.load();
        playRoot.getChildren().addAll(playBoard);
        reactor.putObserverTextFieldForConnection((TextField) playRoot.lookup("#winning"));
    }

    private void addNextButtonToStartBoard(Scene buildScene, AnchorPane startBoard) {
        Button next = (Button) startBoard.lookup("#connectButton");
        next.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String userName = ((TextField) startBoard.lookup("#userName")).getText();
            ((TextField) buildBoard.lookup("#userName")).setText(userName);
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
        reactor.putObserverButtonForConnection(buttonNext);
    }

    public static void run(String[] args) {
        Application.launch(args);
    }
}
