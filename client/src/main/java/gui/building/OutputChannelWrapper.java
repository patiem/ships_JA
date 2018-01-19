package gui.building;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class OutputChannelWrapper {

  Properties properties;

  public OutputChannelWrapper() {
    configureLogOutput();
  }

  private void configureLogOutput() {
    String serverConfigFile = "config.properties";
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
      if(properties.getProperty("outputChannel").equals("err")){

      }
    } catch (IOException e) {
//      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }



}
