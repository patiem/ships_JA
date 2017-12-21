package communication;

import engine.GameRunner;
import engine.Round;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class ConnectionHandler {

    private PlayerTracker playerTracker = new PlayerTracker();

    void acceptConnections(ServerSocket serverSocket) {
        try {
            acceptPlayer(serverSocket);
            acceptPlayer(serverSocket);

        } catch (IOException e) {
            e.printStackTrace(); //LOGER
        }

        createGame();
    }

    private void createGame() {
        Round round = new Round(playerTracker);
        GameRunner gameRunner = new GameRunner(round);
        gameRunner.runGame();
    }

    private void acceptPlayer(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        playerTracker.registerPlayer(socket);
    }
}
