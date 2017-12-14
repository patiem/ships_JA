package starting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartBoard extends Application {
    private final double SCENE_WIDTH = 800;
    private final double SCENE_HEIGHT = 600;
    private final String START_BOARD_URL = "/fxmls/startBoard.fxml";


    @Override
    public void start(Stage stage) throws Exception {

        stage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(START_BOARD_URL));
        AnchorPane startBoard = loader.load();

        StartBoardController startBoardController = loader.getController();
        System.out.println(startBoardController.ipNumber.getText()); //TODO: change to Logger

        Scene scene = new Scene(startBoard, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public static void run(String[] args) {
        Application.launch(args);
    }
}
