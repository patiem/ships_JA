package communication;

import javax.swing.*;
import java.util.Date;

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
    textArea.append(new Date().toString());
    textArea.append("\n");
  }

  @Override
  public void transcript(String message) {
    //todo write to database
    textArea.append(message);
    textArea.append("\n");
  }
}
