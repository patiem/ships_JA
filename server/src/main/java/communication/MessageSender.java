package communication;

import java.io.PrintWriter;

public class MessageSender {
    public void send(PrintWriter printWriter, String messageToSend) {
        printWriter.println(messageToSend);
    }
}
