package messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {

  private static final Logger LOGGER = Logger.getLogger(ServerLogger.class.getName());
  private FileHandler fileHandler;
  private static ServerLogger instance = null;

  {
    configureLoggingToFile();
  }

  private ServerLogger() {
  }

  public static ServerLogger getInstance() {
    if (instance == null) {
      instance = new ServerLogger();
    }
    return instance;
  }

  private void configureLoggingToFile() {

    try {
      fileHandler = new FileHandler("ServerLogs.log", true);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
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

  public FileHandler getFileHandler() {
    return fileHandler;
  }
}
