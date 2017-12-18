package connection;

import javafx.scene.control.Button;
import model.SeaField;
import starting.ConnectEvent;

import java.io.IOException;

public class Client {

    private Connector connector;
    private Sender out;
    private Receiver in;
    private Button nextButton;
    private SeaField lastField;


    public void setup(String host, String port) {
        try {
            connector = SocketConnector.from(host, Integer.valueOf(port));
            in = MessageIn.from(connector);
            out = MessageOut.from(connector);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: Add exception handler
        }
    }

    public void sendMessage(String message)  {
        out.sendMessage(message);
    }

    public void sendMessage(int value)  {
        out.sendMessage(value);
    }

    public void reactOnMessage() {          //TODO: change string to enum
        String message = in.readMessage();
        switch (message) {
            case "CON":
                nextButton.fireEvent(new ConnectEvent("CON"));
                break;
            case "HIT":
                lastField.hit();
                break;
            case "MISSED":
                lastField.missed();
                break;
            case "HIT_AGAIN":
                break;
        }
    }

    public void putObserverForConnection(Button button) {
        this.nextButton = button;
    }

    public void reactOnMessage(SeaField lastField) {
        this.lastField = lastField;
        reactOnMessage();
    }
}
