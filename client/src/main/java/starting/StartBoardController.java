package starting;

import connection.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StartBoardController implements Initializable {
    private final String USER_NAME_VALUE = "Your name";
    private final String IP_NUMBER_VALUE = "localhost";
    private final String PORT_VALUE = "5000";

    private Client client;

    @FXML
    private TextField userName;

    @FXML
    private TextField ipNumber;

    @FXML
    private Button connectButton;

    private String playerName;

    public StartBoardController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.setText(USER_NAME_VALUE);
        ipNumber.setText(IP_NUMBER_VALUE);
        client.setup(IP_NUMBER_VALUE, PORT_VALUE);
        connectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, onMouseClickConnect);
    }

    void setConnection(Client client) {
        client.setup(ipNumber.getText(), PORT_VALUE);
        String userNameValue = userName.getText();
        client.sendMessage(userNameValue);
    }

    EventHandler<MouseEvent> onMouseClickConnect = e -> {
        playerName = userName.getText();
        System.out.println(playerName);
        client.sendMessage(playerName);
        client.reactOnMessage();
    };
}
