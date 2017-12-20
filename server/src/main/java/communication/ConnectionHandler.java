package communication;

import engine.Game;
import engine.Turn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


class ConnectionHandler {

    private PlayerTracker playerTracker;
    private LanguageVersion languageVersion;

    ConnectionHandler() {
        playerTracker = new PlayerTracker();
        languageVersion = new LanguageVersion();
    }

    void acceptConnections(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(languageVersion.getServerRunning());
            acceptPlayer(serverSocket);
            acceptPlayer(serverSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

        createGame();
    }

    private void createGame() {
        Turn turn = new Turn(playerTracker);
        Game game = new Game(turn);
        game.runGame();
    }

    private void acceptPlayer(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        playerTracker.registerPlayer(socket);
    }
}
