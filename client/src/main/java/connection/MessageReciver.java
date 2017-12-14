package connection;

import java.io.IOException;
import java.util.Scanner;

public class MessageReciver implements Receiver {

    Scanner scanner;

    public static MessageReciver from(Connector connector) throws IOException {
        return new MessageReciver(new Scanner(connector.getInStream()));
    }

    private MessageReciver(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readMessage() {
        return scanner.nextLine();
    }

    @Override
    public void distributeMessage() {

    }
}
