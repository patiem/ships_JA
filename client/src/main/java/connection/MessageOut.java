package connection;

import java.io.IOException;
import java.io.PrintWriter;

public class MessageOut implements Sender {

    private final PrintWriter writer;

    public static MessageOut from(Connector connector) throws IOException {
        Boolean autoflush = true;
        return new MessageOut(new PrintWriter(connector.getOutStream(), autoflush));
    }

    private MessageOut(PrintWriter printer) {
        this.writer = printer;
    }

    @Override
    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    @Override
    public void sendMessage(int value) {
        writer.println(value);
        writer.flush();
    }
}
