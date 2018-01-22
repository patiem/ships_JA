package gui;

import messages.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class OutputChannelDispatcher {

  private ServerLogger serverLogger = ServerLogger.getInstance();
  private OutputSelector systemErr = new SystemErr(this);
  private OutputSelector sysOut = new SysOut(this);
  private OutputSelector currentSelection;

  {
    String serverConfigFile = "config.properties";
    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
      if (properties.getProperty("outputChannel").equals("err")) {
        currentSelection = systemErr;
      } else {
        currentSelection = sysOut;
      }
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
    }
  }

  public void printToDesiredOutput(String message) {
    currentSelection.printToDesiredOutput(message);
  }
}
