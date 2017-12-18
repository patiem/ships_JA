package communication;

public class Server {

    private static final int PORT = 5000;

    public void runServer() {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        connectionHandler.acceptConnections(PORT);
        connectionHandler.handleGameEvent();
        }
    }




