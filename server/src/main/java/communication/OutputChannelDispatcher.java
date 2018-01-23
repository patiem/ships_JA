package communication;

import messages.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class OutputChannelDispatcher {

  private static ServerLogger serverLogger = ServerLogger.getInstance();

  public static OutputSelector getOutput(){
    String serverConfigFile = "config.properties";
    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
      if (properties.getProperty("outputChannel").equals("err")) {
        return new SystemErr();
      } else {
        return new SysOut();
      }
    } catch (IOException e) {
      serverLogger.log(Level.SEVERE, e.getMessage());
      return new SysOut();
    }
  }
}
