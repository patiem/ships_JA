package messages;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ClientLogger {

  private static final Logger LOGGER = Logger.getLogger(ClientLogger.class.getName());
  private FileHandler fileHandler;
  private static ClientLogger instance = null;

  {
    configureLoggingToFile();
  }

  private ClientLogger() {
  }

  public static ClientLogger getInstance() {
    if (instance == null) {
      instance = new ClientLogger();
    }
    return instance;
  }

  private void configureLoggingToFile() {
    try {
      fileHandler = new FileHandler("ClientLogs.log", false);
    } catch (IOException e) {
      e.printStackTrace();
    }
    LOGGER.addHandler(fileHandler);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
  }

  public void info(String logMessage) {
    LOGGER.log(Level.INFO, logMessage);
  }

  public void log(Level level, String logMessage) {
    LOGGER.log(level, logMessage);
  }

}
