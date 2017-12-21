package communication;

import fleet.CustomFleet;
import fleet.Fleet;
import json.CustomerJsonParser;
import json.InitMessage;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class PlayerTracker {

    private Deque<PlayerClient> players = new ArrayDeque<>();
    private MessageReceiver messageReceiver = new MessageReceiver();


    void registerPlayer(Socket socket) {
        final String playerIsConnected = "CON";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            String gameStartingObjectAsString = messageReceiver.receiveMessage(reader);
            System.out.println(gameStartingObjectAsString); //TODO: change to Logger
            CustomerJsonParser jsonParser = new CustomerJsonParser();
            InitMessage initMessage = jsonParser.parse(gameStartingObjectAsString, InitMessage.class);
            Fleet playerFleet = new CustomFleet(initMessage.getFleetModel());
            PlayerClient playerClient = new PlayerClient(initMessage.getName(), socket, reader, playerFleet);

            addPlayer(playerClient);
            playerClient.sendMessageToPlayer(playerIsConnected);
            System.out.println("PlayerClient added: " + playerClient.getName()); //TODO: change to Logger


        } catch (IOException e) {
            e.printStackTrace(); //TODO: add ExceptionHandling
        }
    }


    public void sendMessageToCurrentPlayer(String message) {
        players.peekFirst().sendMessageToPlayer(message);
    }

    private void addPlayer(PlayerClient playerClient) {
        players.add(playerClient);
    }

    public Socket getCurrentSocket() {
        return players.peekFirst().getSocket();
    }

    public Fleet getFleetUnderFire() {
        return players.peekLast().getFleet();
    }


    public void switchPlayers() {
        PlayerClient current = players.pollFirst();
        players.addLast(current);
    }

    public String currentPlayerName() {
        return players.peekFirst().getName();
    }


    public PlayerClient getCurrentPlayerClient() {
        return players.peekFirst();
    }
}


