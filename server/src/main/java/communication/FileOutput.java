package communication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements Output {
private File file;
private BufferedWriter bufferedWriter;
  public FileOutput() throws IOException {
     file = new File("logs" + File.separator +"gameFlow.txt");
     bufferedWriter = new BufferedWriter(new FileWriter(file));
  }

  @Override
  public void transcript(String message) {

  try {
bufferedWriter.append(message);
bufferedWriter.newLine();
  }catch (IOException e) {
    //TODO: add logger
    e.printStackTrace();
  }



    //System.out.println(message);

  }
}
