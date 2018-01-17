package model;

/**
 * It represents a player.
 *
 * @version 1.5
 */
public class Player {
  private Fleet fleet;
  private String name;

  public Player(Fleet fleet, String name) {
    this.fleet = fleet;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Fleet getFleet() {
    return fleet;
  }
}
