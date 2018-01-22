package messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ClientLogger {

  private static final Logger LOGGER = Logger.getLogger(ClientLogger.class.getName());
  private Properties properties;
  private FileHandler fileHandler;
  private static ClientLogger instance = null;

  {
    configureLogOutput();
  }

  private ClientLogger() { }

  public static ClientLogger getInstance() {
    if(instance == null) {
      instance = new ClientLogger();
    }
    return instance;
  }

  private void configureLogOutput() {
    String serverConfigFile = "config.properties";
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
      if(properties.getProperty("logsToFile").equals("yes")){
        configureLoggingToFile();
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  private void configureLoggingToFile() throws IOException {
    fileHandler = new FileHandler("ClientLogs.log", false);
    LOGGER.addHandler(fileHandler);
    SimpleFormatter formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
  }

  public void info(String logMessage) {
    LOGGER.log(Level.INFO,logMessage);
  }

  public void log(Level level, String logMessage) {
    LOGGER.log(level,logMessage);
  }


}
