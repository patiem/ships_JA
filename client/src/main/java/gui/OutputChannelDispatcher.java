package gui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OutputChannelDispatcher {

  private Properties properties;
  private OutputSelector systemErr = new SystemErr(this);
  private OutputSelector javaFxPopUp = new JavaFxPopUp(this);
  private OutputSelector noSelection = new NoSelection(this);
  private OutputSelector currentSelection;

  {
    String serverConfigFile = "config.properties";
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);

    try {
      properties.load(config);
      if(properties.getProperty("outputChannel").equals("err")){
        currentSelection = systemErr;
      } else if(properties.getProperty("outputChannel").equals("popup")){
        currentSelection = javaFxPopUp;
      } else {
        currentSelection = noSelection;
      }
    } catch (IOException e) {
//      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

 public void printToDesiredOutput(String message) {
    currentSelection.printToDesiredOutput(message);
  }
}
