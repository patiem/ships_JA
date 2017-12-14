package connection;

import java.io.IOException;
import java.io.PrintWriter;

public class MessageWriter implements Sender {

    private final PrintWriter writer;

    public static MessageWriter from(Connector connector) throws IOException {
        Boolean autoflush = true;
        return new MessageWriter(new PrintWriter(connector.getOutStream(), autoflush));
    }

    private MessageWriter(PrintWriter printer) {
        this.writer = printer;
    }

    @Override
    public void sendMessage(String message) {
        writer.println(message);
    }
}
