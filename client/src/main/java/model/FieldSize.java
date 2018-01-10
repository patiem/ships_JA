package model;

public enum FieldSize {
  SMALL(20),
  BIG(30);

  private int value;

  FieldSize(int value){
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
