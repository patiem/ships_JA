package communication;

import java.io.PrintWriter;

class MessageSender {
    void send(PrintWriter printWriter, String messageToSend) {
        printWriter.println(messageToSend);
    }
}
