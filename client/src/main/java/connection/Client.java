package connection;

import java.io.*;
import java.util.Scanner;

public class Client {

    static final String CHARSET = "UTF-8";

    private Connector connector;
    private Sender out;
    private Receiver in;

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

    private static boolean loopCondition = true;

    //Dummy console GUI for testing
    public static void run() throws IOException {

        String HOST = "localhost"; //For testing change this to host IP
        Integer PORT = 5000;

        System.out.println("Welcome to the battleships game, please provide your name");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        Connector connector = SocketConnector.from(HOST, PORT);
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
}
