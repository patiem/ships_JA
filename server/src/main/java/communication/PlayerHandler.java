package communication;

import fleet.Fleet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerHandler {

    private List<Client> clients = new ArrayList<>();
    private Client currentClient;
    private MessageReceiver messageReceiver = new MessageReceiver();

    public List<Client> getClients() {
        return clients;
    }


    public void registerPlayer(Socket socket) {
        String playerIsConnected = "CON";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new PrintWriter(socket.getOutputStream(), false));
            String playerName = messageReceiver.receiveMessage(reader);
            Client newClient = new Client(playerName, socket, writer, reader);
            addPlayer(newClient);
            newClient.sendMessageToPlayer(playerIsConnected);
            System.out.println("Client added: " + playerName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessageToCurrentPlayer(String message) {
        currentClient.sendMessageToPlayer(message);
    }

    public void sendMessageToOtherPlayer(String message) {
        clients.get(1).sendMessageToPlayer(message);
    }

    private void addPlayer(Client client) {
        if (clients.isEmpty())
            currentClient = client;
        clients.add(client);
    }

    public Socket getCurrentSocket() {
        return currentClient.getSocket();
    }

    public Fleet getCurrentFleet() {
        return currentClient.getFleet();
    }

    public Socket getWaitingPlayerSocket() {
        return clients.get(1).getSocket();
    }

    public void switchPlayers() {
        Collections.reverse(clients);
        currentClient = clients.get(0);
    }

    public String currentPlayerName() {
        return currentClient.getName();
    }


    public Client getCurrentClient() {
        return currentClient;
    }
}


