package communication;

import javax.swing.*;

public class TranscriptPanel extends JFrame {

  private JTextArea  textArea;

  public TranscriptPanel() {
    textArea = new JTextArea();
    add(textArea);
    this.setSize(500, 2000);
    // pack();
    setVisible(true);
  }

  public void write(String message) {
    textArea.append(message);
    textArea.append("\n");
  }
}
