package communication;

import javax.swing.*;

public class GraphicalOutput extends JFrame implements Output {

  private JTextArea  textArea;

  public GraphicalOutput() {

    textArea = new JTextArea();
    add(textArea);
    this.setSize(500, 2000);
    JScrollPane scroll = new JScrollPane (textArea,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    add(scroll);
    setVisible (true);
    textArea.setEditable(false);
  }

  @Override
  public void transcript(String message) {
    textArea.append(message);
    textArea.append("\n");
  }
}
