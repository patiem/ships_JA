package messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageVersion {

  private static final Logger LOGGER = Logger.getLogger(LanguageVersion.class.getName());
  private String name;
  private String wait;
  private String play;
  private String win;
  private String loss;
  private String nameLabel;
  private String connect;

  public LanguageVersion() {
    setLanguage();
  }

  private void setLanguage() {
    String serverConfigFile = "config.properties";
    String languageConfig;
    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,e.getMessage());
    }
    String languageVersion = properties.getProperty("languageVersion");
    if (languageVersion.equals("Polish")) {
      languageConfig = "Polish.properties";
    } else {
      languageConfig = "English.properties";
    }
    InputStream language = ClassLoader.getSystemResourceAsStream(languageConfig);
    try {
      properties.load(language);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,e.getMessage());
    }
    name = properties.getProperty("name");
    wait = properties.getProperty("wait");
    play = properties.getProperty("play");
    win = properties.getProperty("win");
    loss = properties.getProperty("loss");
    nameLabel = properties.getProperty("nameLabel");
    connect = properties.getProperty("connect");
  }

  public String getNameMessage() {
    return name;
  }

  public String getWaitMessage() {
    return wait;
  }

  public String getPlayMessage() {
    return play;
  }

  public String getWinMessage() {
    return win;
  }

  public String getLossMessage() {
    return loss;
  }

  public String getConnectMessage() {
    return connect;
  }

  public String getNameLabelMessage() {
    return nameLabel;
  }
}
