package communication;

import fleet.Fleet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerTracker {

    private List<PlayerClient> playerClients = new ArrayList<>();
    private PlayerClient currentPlayerClient;
    private MessageReceiver messageReceiver = new MessageReceiver();

    public List<PlayerClient> getPlayerClients() {
        return playerClients;
    }


    public void registerPlayer(Socket socket) {
        String playerIsConnected = "CON";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            String playerName = messageReceiver.receiveMessage(reader);
            PlayerClient newPlayerClient = new PlayerClient(playerName, socket, reader);

            addPlayer(newPlayerClient);
            newPlayerClient.sendMessageToPlayer(playerIsConnected);
            System.out.println("PlayerClient added: " + playerName);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessageToCurrentPlayer(String message) {
        currentPlayerClient.sendMessageToPlayer(message);
    }

    public void sendMessageToOtherPlayer(String message) {
        playerClients.get(1).sendMessageToPlayer(message);
    }

    private void addPlayer(PlayerClient playerClient) {
        if (playerClients.isEmpty())
            currentPlayerClient = playerClient;
        playerClients.add(playerClient);
    }

    public Socket getCurrentSocket() {
        return currentPlayerClient.getSocket();
    }

    public Fleet getCurrentFleet() {
        return currentPlayerClient.getFleet();
    }

    public Socket getWaitingPlayerSocket() {
        return playerClients.get(1).getSocket();
    }

    public void switchPlayers() {
        Collections.reverse(playerClients);
        currentPlayerClient = playerClients.get(0);
    }

    public String currentPlayerName() {
        return currentPlayerClient.getName();
    }


    public PlayerClient getCurrentPlayerClient() {
        return currentPlayerClient;
    }
}


