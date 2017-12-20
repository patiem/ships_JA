package starting;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class StartBoardController implements Initializable {
    private final String USER_NAME_VALUE = "Your name";
    private final String IP_NUMBER_VALUE = "localhost";

    private final Client client;

    @FXML
    private TextField userName;

    @FXML
    private TextField ipNumber;


    public StartBoardController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.setText(USER_NAME_VALUE);
        ipNumber.setText(IP_NUMBER_VALUE);
        client.setup(IP_NUMBER_VALUE);
    }


}
