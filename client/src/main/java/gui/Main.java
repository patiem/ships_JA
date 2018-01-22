package gui;

import gui.starting.StartBoard;
import messages.ClientLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configure application.
 *
 * @version 1.5
 */
class Main {

  private static final ClientLogger LOGGER = ClientLogger.getInstance();
  private static final String SERVER_CONFIG_FILE = "config.properties";
  private static final String PATH_TO_PROPS = "utils/src/main/resources/";

  public static void main(String[] args) throws IOException {

    if (args.length > 0) {
      setupNewIpForHost(args[0]);
    }
    try {
      if (HostValidator.isServerConfigValid(PATH_TO_PROPS + SERVER_CONFIG_FILE)) {
        StartBoard.run(args);
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
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
