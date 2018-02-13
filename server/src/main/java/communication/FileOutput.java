package communication;

import messages.ServerLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;

public class FileOutput implements Output {
  private static ServerLogger serverLogger = ServerLogger.getInstance();

  private File file;

  public FileOutput() throws IOException {
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
      //todo write to database
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }

  public void transcript(Date message) {
    try (FileWriter fw = new FileWriter(file, false);
         PrintWriter out = new PrintWriter(new BufferedWriter(fw))) {
      //todo write to database
      out.println(message.toString());
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }
}
