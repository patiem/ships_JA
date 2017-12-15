package connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageIn implements Receiver {

    BufferedReader scanner;

    public static MessageIn from(Connector connector) throws IOException {
        return new MessageIn(new BufferedReader(new InputStreamReader(connector.getInStream())));
    }

    private MessageIn(BufferedReader scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readMessage() {
        try {
            String message = scanner.readLine();
            System.out.println(message);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void distributeMessage() {

    }
}
