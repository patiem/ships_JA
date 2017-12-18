package communication;

import fleet.Fleet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerHandler {

    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public List<Player> getPlayers() {
        return players;
    }

    public void registerPlayer(Socket socket) {
        String playerIsConnected = "CON";
        MessageReceiver messageReceiver = new MessageReceiver();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String playerName = messageReceiver.receiveMessage(reader);
            Player newPlayer = Player.playerBuilder(playerName, socket);
            addPlayer(newPlayer);
            newPlayer.sendMessageToPlayer(playerIsConnected);
            System.out.println("Player added: " + playerName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToCurrentPlayer(String message) {
        currentPlayer.sendMessageToPlayer(message);
    }

    public void sendMessageToOtherPlayer(String message) {
        players.get(1).sendMessageToPlayer(message);
    }

    private void addPlayer(Player player) {
        if (players.isEmpty())
            currentPlayer = player;
        players.add(player);
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

    public String currentPlayerName() {
        return currentPlayer.getName();
    }
}


