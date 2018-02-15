package communication;

import persistence.database.query.dao.TranscriptDao;
import persistence.session.factory.configuration.SessionFactoryProvider;
import persistence.transcript.model.Transcript;

import javax.swing.*;
import java.util.Date;

public class GraphicalOutput extends JFrame implements Output {

  private JTextArea  textArea;
  private final transient TranscriptDao transcriptDao;

  GraphicalOutput() {
    transcriptDao = new TranscriptDao(SessionFactoryProvider.provide());
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
    transcriptDao.save(new Transcript(message, new Date()));
    textArea.append(message);
    textArea.append("\n");
  }
}
