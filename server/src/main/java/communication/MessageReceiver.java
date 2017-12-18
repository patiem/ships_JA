package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver {

    BufferedReader bufferedReader;

    public void receiveGameEvent(Socket currentSocket) {

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(currentSocket.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String receiveMessage() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
