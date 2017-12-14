package starting;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartBoardController implements Initializable {
    private final String USER_NAME_VALUE = "Your name";
    private final String IP_NUMBER_VALUE = "localhost";
    private final String PORT_VALUE = "5000";

    private Client client;

    @FXML
    TextField userName;

    @FXML
    TextField ipNumber;

    @FXML
    TextField portNumber;

    @FXML
    Button connectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new Client();
        userName.setText(USER_NAME_VALUE);
        ipNumber.setText(IP_NUMBER_VALUE);
        portNumber.setText(PORT_VALUE);
    }

    @FXML
    void connect(MouseEvent event) throws IOException {
        client.setup(ipNumber.getText(), portNumber.getText());
        String userNameValue = userName.getText();
        client.sendMessage(userNameValue);
    }
}
