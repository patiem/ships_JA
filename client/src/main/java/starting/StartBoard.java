package starting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartBoard extends Application {

    private final double SCENE_WIDTH = 800;
    private final double SCENE_HEIGHT = 600;
    private final String START_BOARD_URL = "/fxmls/startBoard.fxml";
    private final String PLAY_BOARD_URL = "/fxmls/playBoardEmpty.fxml";

    @Override
    public void start(Stage stage) throws Exception {

        stage.setResizable(false);

        Group startRoot = new Group();
        Group playRoot = new Group();

        Scene startScene = new Scene(startRoot, SCENE_WIDTH, SCENE_HEIGHT);
        Scene playScene = new Scene(playRoot, SCENE_WIDTH, SCENE_HEIGHT);

        FXMLLoader startLoader = new FXMLLoader(getClass().getResource(START_BOARD_URL));
        AnchorPane startBoard = startLoader.load();
        addNextButtonToStartBoard(stage, playScene, startBoard);
        startRoot.getChildren().addAll(startBoard);

        FXMLLoader playLoader = new FXMLLoader(getClass().getResource(PLAY_BOARD_URL));
        AnchorPane playBoard = playLoader.load(); //TODO to uruchamia drugi wątek!!!!
        playRoot.getChildren().addAll(playBoard);

        stage.setTitle("FXML Welcome");
        stage.setScene(startScene);stage.show();
    }

    private void addNextButtonToStartBoard(Stage stage, Scene playScene, AnchorPane startBoard) {
        Button buttonNext = new Button("Next");
        buttonNext.setOnAction(event -> stage.setScene(playScene));
        VBox connectPanel = (VBox) startBoard.lookup("#connectPanel");
        connectPanel.getChildren().add(buttonNext);
    }

    public static void run(String[] args) {
        Application.launch(args);
    }
}
