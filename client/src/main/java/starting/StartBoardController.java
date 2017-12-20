package starting;

import connection.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class StartBoardController implements Initializable {

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
        String USER_NAME_VALUE = "Your name";
        userName.setText(USER_NAME_VALUE);
        String IP_NUMBER_VALUE = "localhost";
        ipNumber.setText(IP_NUMBER_VALUE);
        client.setup(IP_NUMBER_VALUE);
    }


}
