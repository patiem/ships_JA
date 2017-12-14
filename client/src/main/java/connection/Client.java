package connection;

import java.io.*;
import java.util.Scanner;

public class Client {

    static final String CHARSET = "UTF-8";

    Connector connector;
    Sender sender;
    Receiver reader;
    Receiver readerOne;
    Receiver readerTwo;

    public void setup(String host, String port) {
        try {
            connector = SocketConnector.from(host, Integer.valueOf(port));
            reader = MessageReciver.from(connector);
            sender = MessageWriter.from(connector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message)  {
        sender.sendMessage(message);
    }

    private static boolean loopCondition = true;

    public static void run() throws IOException {

        System.out.println("Welcome to the battleships game, please provide your name");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        Connector connector = SocketConnector.from("localhost", 5000);
        Receiver messegaScannerOne = MessageReciver.from(connector);
        Sender writer = MessageWriter.from(connector);
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
