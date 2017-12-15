package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MessageIn implements Receiver {

    private final BufferedReader scanner;

    static MessageIn from(Connector connector) throws IOException {
        return new MessageIn(new BufferedReader(new InputStreamReader(connector.getInStream())));
    }

    private MessageIn(BufferedReader scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readMessage() {
        try {
            return scanner.readLine();
        } catch (IOException e) {
            e.printStackTrace(); //TODO: add exception handler
        }
        return "";
    }

    @Override
    public void distributeMessage() {

    }
}
