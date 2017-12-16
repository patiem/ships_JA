package communication;

import fleet.Fleet;
import fleet.HardcodedFleet;

import java.net.Socket;

class Player {

    private String name;
    private Socket socket;
    private final Fleet fleet;

    Player(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
        this.fleet = new HardcodedFleet();
    }

    String getName() {
        return name;
    }

    Fleet getFleet() {
        return fleet;
    }

    Socket getSocket() {
        return socket;
    }
}
