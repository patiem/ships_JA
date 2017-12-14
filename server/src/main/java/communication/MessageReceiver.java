package communication;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageReceiver {
    public String receiveMessage(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
