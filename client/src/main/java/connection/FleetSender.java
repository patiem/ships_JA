package connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import messages.ConnectionMessage;
import json.JsonGeneratorAdapter;
import model.FleetMapper;
import model.FleetModel;
import model.Player;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * It sends a player's fleet to the server.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class FleetSender {
  private static final Logger LOGGER = Logger.getLogger(FleetSender.class.getName());

  private Client client;
  private Player player;

  public FleetSender(Client client, Player player) {
    this.client = client;
    this.player = player;
  }

  public void sendFleetToServer() {
    FleetModel fleetModel = FleetMapper.mapToFleetModel(player.getFleet());
    ConnectionMessage messageWithFleet = new ConnectionMessage(player.getName(), fleetModel);
    JsonGeneratorAdapter jsonGenerator = new JsonGeneratorAdapter();

    try {
      client.sendMessage(jsonGenerator.createJson(messageWithFleet, new ObjectMapper()));
    } catch (JsonProcessingException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }
}
