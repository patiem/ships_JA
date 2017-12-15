package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerHandler {

    private List<Player> players = new ArrayList<>();
    private BufferedReader reader;
    private Player currentPlayer;

    public List<Player> getPlayers() {
        return players;
    }

    public void registerPlayer(Socket socket) {
        MessageReceiver messageReceiver = new MessageReceiver();

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: ExceptionHandler
        }

        String playerName = messageReceiver.receiveMessage(reader);
        Player newPlayer = new Player(playerName, socket);
        addPlayer(newPlayer);

        System.out.println("Player added: " + playerName); //TODO: add Logger

        sendNotification(socket);
    }

    private void addPlayer(Player player) {
        if (players.isEmpty())
            currentPlayer = player;

        players.add(player);
    }

    private void sendNotification(Socket socket) {
        MessageSender messageSender = new MessageSender();
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String messageToSend = LanguageVersion.CONFIRM_CONNECTION;  //TODO: add message :wait for second communication.Player"
        messageSender.send(printWriter, messageToSend);
    }

    public Socket getCurrentSocket() {
        return currentPlayer.getSocket();
    }

    public Fleet getCurrentFleet() {
        return currentPlayer.getFleet();
    }

    public Socket getWaitingPlayerSocket() {
        return players.get(1).getSocket();
    }

    public void switchPlayers() {
        Collections.reverse(players);
        currentPlayer = players.get(0);
    }
}


