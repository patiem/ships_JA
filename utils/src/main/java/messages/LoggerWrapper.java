package messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerWrapper {

  private static final Logger LOGGER = Logger.getLogger(LoggerWrapper.class.getName());
  private Properties properties;
  private FileHandler fileHandler;
  private boolean fileOutput;

  public LoggerWrapper() {
    configureLogOutput();
  }

  private void configureLogOutput() {
    String serverConfigFile = "config.properties";
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
      if(properties.getProperty("logsToFile").equals("yes")){
        fileOutput = true;
        configureLoggingToFile();
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  private void configureLoggingToFile() throws IOException {
    fileHandler = new FileHandler("ShipWrecks.log", true);
    LOGGER.addHandler(fileHandler);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
  }

  public void logShotInfo(final String shot, final String shotResult, String playerName) {
    String messageTemplate = "Player: %s, shot: position: %s, shotState: %s;";
    String logMessage = String.format(messageTemplate, playerName,
        shot, shotResult);
    LOGGER.log(Level.INFO,logMessage);
  }

  public void info(String logMessage) {
    LOGGER.log(Level.INFO,logMessage);
  }

  public void log(Level level, String logMessage) {
    LOGGER.log(level,logMessage);
  }
}
