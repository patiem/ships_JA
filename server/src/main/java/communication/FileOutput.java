package communication;

import messages.ServerLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

public class FileOutput implements Output {
  private static ServerLogger serverLogger = ServerLogger.getInstance();

  private File file;

  public FileOutput() throws IOException {
    File directory = new File("logs");
    directory.mkdir();
    file = new File(directory.getPath() + File.separator + "gameFlow.txt");
  }

  @Override
  public void transcript(String message) {
    try (FileWriter fw = new FileWriter(file, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {
      out.println(message);
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }
}
