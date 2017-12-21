package playing;

import connection.Client;
import model.MessageReactor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.SeaField;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayBoardController implements Initializable {

    private final Client client;
    private final MessageReactor reactor;

    @FXML
    private GridPane shipBoard;

    private SeaField lastTarget;

    public PlayBoardController(Client client, MessageReactor reactor) {
        this.client = client;
        this.reactor = reactor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                    reactor.reactOnMessage(field, client.getMessage());
                });
                shipBoard.getChildren().add(field);
                GridPane.setConstraints(field, i, n);
            }
        }
    }

    private void sendPositionToServer(SeaField seaField) {
        client.sendMessage(seaField.calculateListPosition().toString());
    }
}

