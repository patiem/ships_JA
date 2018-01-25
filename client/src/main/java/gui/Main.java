package gui;

import gui.starting.StartBoard;
import messages.ClientLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Configure application.
 *
 * @version 1.5
 */
class Main {

  private static final ClientLogger LOGGER = ClientLogger.getInstance();
  private static final String SERVER_CONFIG_FILE = "config.properties";
  private static final String PATH_TO_PROPS = "utils/src/main/resources/";

  public static void main(String[] args)  {

    try {

      if (args.length > 0) {
      setupNewIpForHost(args[0]);
    }
      if (HostValidator.isServerConfigValid(PATH_TO_PROPS + SERVER_CONFIG_FILE)) {
        StartBoard.run(args);
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
      e.printStackTrace();
    }
  }

  private static void setupNewIpForHost(String newIpForHost) throws IOException {

    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);

    properties.load(config);
    properties.setProperty("IP", newIpForHost);

    FileWriter fileWriter = new FileWriter(PATH_TO_PROPS + SERVER_CONFIG_FILE);
    properties.store(fileWriter, null);
    fileWriter.flush();
    fileWriter.close();

  }
}
