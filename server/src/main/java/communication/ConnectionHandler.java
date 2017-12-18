package communication;

import communication.engine.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ConnectionHandler {

    private PlayerHandler playerHandler;
    private LanguageVersion languageVersion;
    private Map<Socket, List<String>> allHits;


    public ConnectionHandler() {
        playerHandler = new PlayerHandler();
        languageVersion = new LanguageVersion();
        allHits = new HashMap<>();
    }

    void acceptConnections(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(languageVersion.getServerRunning());
            createAListOfHits(serverSocket);
            createAListOfHits(serverSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

        createGame();
    }


    private void createGame() {

        Game game = new Game(playerHandler, allHits);
        game.handleGameEvent();
    }


    // TODO: move this method elsewhere
    void createAListOfHits(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        List<String> hits = new ArrayList<>();
        allHits.put(socket, hits);
        playerHandler.registerPlayer(socket);
    }


}
