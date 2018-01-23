package communication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOutput implements Output {
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
      //TODO: add Logger
    }
  }
}
