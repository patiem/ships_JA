package communication;

public class Server {

    private static final int PORT = 5000;
    private boolean isServerRunning = true;

    public void runServer() {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        connectionHandler.acceptConnections(PORT);
        connectionHandler.handleGameEvent();
        }
    }




