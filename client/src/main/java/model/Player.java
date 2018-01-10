package model;

/**
 * It represents a player.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
public class Player {
  private FleetCreator fleetCreator;
  private String name;

  public Player(FleetCreator fleetCreator, String name) {
    this.fleetCreator = fleetCreator;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public FleetCreator getFleetCreator() {
    return fleetCreator;
  }
}
