package communication;

import messages.ServerLogger;
import persistence.database.query.dao.TranscriptDao;
import persistence.session.factory.configuration.SessionFactoryProvider;
import persistence.transcript.model.Transcript;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;

public class FileOutput implements Output {
  private static final String START_OF_THE_TRANSCRIPT_WRITTEN_TO_FILE = "Start of the Transcript written to File";
  private static ServerLogger serverLogger = ServerLogger.getInstance();

  private File file;
  private TranscriptDao transcriptDao;

  FileOutput() throws IOException {
    transcriptDao = new TranscriptDao(SessionFactoryProvider.provide());
    File directory = new File("logs");
    directory.mkdir();
    file = new File(directory.getPath() + File.separator + "gameFlow.txt");
    transcript(new Date());
  }

  @Override
  public void transcript(String message) {
    try (FileWriter fw = new FileWriter(file, true);
         PrintWriter out = new PrintWriter(new BufferedWriter(fw))) {
      out.println(message);
      transcriptDao.save(new Transcript(message, new Date()));
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }

  public void transcript(Date message) {
    try (FileWriter fw = new FileWriter(file, false);
         PrintWriter out = new PrintWriter(new BufferedWriter(fw))) {
      out.println(message.toString());
      transcriptDao.save(new Transcript(START_OF_THE_TRANSCRIPT_WRITTEN_TO_FILE, message));
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }
}
