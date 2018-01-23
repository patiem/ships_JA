package communication;

import javax.swing.*;

public class TranscriptPanel extends JFrame implements Output {

  private JTextArea  textArea;

  public TranscriptPanel() {
    textArea = new JTextArea();
    add(textArea);
    this.setSize(500, 2000);
    setVisible(true);
  }

  @Override
  public void transcript(String message) {
    textArea.append(message);
    textArea.append("\n");
  }
}
