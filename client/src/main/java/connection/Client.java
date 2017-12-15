package connection;

import javafx.scene.control.Button;
import playing.SeaField;
import starting.ConnectEvent;

import java.io.*;
import java.util.Scanner;

public class Client {

    static final String CHARSET = "UTF-8";

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
            e.printStackTrace();
        }
    }

    public void sendMessage(String message)  {
        out.sendMessage(message);
    }

    public void sendMessage(int value)  {
        out.sendMessage(value);
    }

    public void reactOnMessage() {
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
        }
    }

    private static boolean loopCondition = true;

    public static void run() throws IOException {

        System.out.println("Welcome to the battleships game, please provide your name");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        Connector connector = SocketConnector.from("localhost", 5000);
        Receiver messegaScannerOne = MessageIn.from(connector);
        Sender writer = MessageOut.from(connector);
        writer.sendMessage(name);

        String response = messegaScannerOne.readMessage();
        System.out.println(response);

        String response2 = messegaScannerOne.readMessage();
        System.out.println(response2);

        boolean isMyTurn = response2.contains("Your turn");
        System.out.println("turn: " + isMyTurn);

        while (loopCondition) {
            if (isMyTurn){
                String firedShot = scan.nextLine();
                writer.sendMessage(firedShot);
            }

            String nextStepMessage = messegaScannerOne.readMessage();
            System.out.println(nextStepMessage);

            isMyTurn = response2.contains("Your turn");
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
