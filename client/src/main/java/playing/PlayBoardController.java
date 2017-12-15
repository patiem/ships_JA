package playing;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayBoardController implements Initializable {

    private Client client;

    @FXML
    GridPane shipBoard;


    SeaField lastTarget;

    public PlayBoardController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new Client();
        client.setup("localhost", "5000");
        populateSeaWithSeaFields();
    }

    private void populateSeaWithSeaFields() {
        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                SeaField field = new SeaField(i, n);
                field.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    lastTarget = field;
                    sendPositionToServer(field);
                    field.marked();
                    sendPositionToServer(field);
                    client.reactOnMessage(field);
                });
                shipBoard.getChildren().add(field);
                GridPane.setConstraints(field, i, n);
            }
        }
    }

    private void sendPositionToServer(SeaField seaField) {
        client.sendMessage(seaField.caluclateListPosition());
    }

    private FieldPosition makePosition(SeaField seaField) {
        return new FieldPosition((int) seaField.getX(), (int) seaField.getY());
    }
}
