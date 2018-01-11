package gui.fields;

public interface ClickableField extends Field {

  void makeUnclickable();
  void makeClickable();
  void markAsBound(boolean isMarkedAsBound);
}
