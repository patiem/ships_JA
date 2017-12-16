package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver {
    public String receiveMessage(BufferedReader reader) {
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
