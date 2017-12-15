package communication;

import fleet.CustomFleet;
import fleet.Fleet;
import fleet.HardcodedFleet;

import java.net.Socket;

public class Player {

    private String name;
    private Socket socket;
    private final Fleet fleet;

    public Player(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
        this.fleet = new HardcodedFleet();
    }

    public String getName() {
        return name;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public Socket getSocket() {
        return socket;
    }
}
