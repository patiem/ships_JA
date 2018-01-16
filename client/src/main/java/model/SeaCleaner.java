package model;

public class SeaCleaner {
  private Sea sea;

  public SeaCleaner(Sea sea) {
    this.sea = sea;
  }

  public void clean() {
    sea.clearSea();
  }
}
