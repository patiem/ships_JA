package communication;

        import com.google.common.collect.MultimapBuilder;
        import com.google.common.collect.SetMultimap;
        import fleet.Fleet;


        import java.io.IOException;
        import java.io.PrintWriter;
        import java.net.ServerSocket;
        import java.net.Socket;

class ConnectionHandler {
    private PlayerHandler playerHandler = new PlayerHandler();
    private LanguageVersion languageVersion = new LanguageVersion();
    Socket currentSocket;

    SetMultimap<Socket, String> allHits =
            MultimapBuilder.hashKeys().hashSetValues(50).build();

    void acceptConnections(int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(languageVersion.getServerRunning());

            registerPlayer(serverSocket);
            registerPlayer(serverSocket);

        } catch (IOException e) {
            e.printStackTrace(); //TODO: add ExceptionHandler
        }
    }

    void handleGameEvent() {
        MessageReceiver messageReceiver = new MessageReceiver();
        int i = 0;

        while(true) {
            Socket currentSocket = playerHandler.getCurrentSocket();
            String hit = messageReceiver.receiveMessage(currentSocket);
            if(!(allHits.containsEntry(currentSocket, hit))) {
                allHits.put(currentSocket, hit);
                Integer toMark = Integer.parseInt(hit);
                Fleet fleet = playerHandler.getCurrentFleet();

                HitChecker hitChecker = new HitChecker(fleet);
                ShotState shotState = hitChecker.checkShot(toMark);

                showInfoAboutCurrentShot(hit, shotState, i);
                playerHandler.sendMessageToCurrentPlayer(shotState.toString());
                if (!(shotState == ShotState.HIT)) {
                    playerHandler.switchPlayers();
                }
                i++;
            }
        }
    }

    private void showInfoAboutCurrentShot(String hit, ShotState shotState, int i) {
        System.out.println(System.out.printf("%d. pl: %s, shoot: %s, %s", i, playerHandler.currentPlayerName(), hit, shotState));
    }

    private void registerPlayer(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        allHits.put(socket, "100");
        playerHandler.registerPlayer(socket);
    }

    private void sendFireNotification(Socket socket, String messageToSend) {
        MessageSender messageSender = new MessageSender();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageSender.send(printWriter, messageToSend);
    }
}
