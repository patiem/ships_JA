package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver {





//    public void sendMessageToCurrentPlayer(String message) {
//        currentPlayer.sendMessageToPlayer(message);
//    }

    public String receiveMessage(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
