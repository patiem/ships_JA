package building;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FleetDrop extends Application {

    private final double SCENE_WIDTH = 800;
    private final double SCENE_HEIGHT = 600;
    private final String START_BOARD_URL = "/fxmls/buildBoardAllShips.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Build Board");
        stage.setResizable(false);

        final FXMLLoader loader = new FXMLLoader(getClass().getResource(START_BOARD_URL));
        final AnchorPane playBoard = loader.load();

        final Scene scene = new Scene(playBoard, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Playing");
        stage.setScene(scene);
        stage.show();
    }
}
