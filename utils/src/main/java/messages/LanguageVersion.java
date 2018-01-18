package messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageVersion {

  private static final String SERVER_CONFIG_FILE = "config.properties";
  private static String LANGUAGE_CONFIG;
  private Properties properties;

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
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);
    try {
      properties.load(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String languageVersion = properties.getProperty("languageVersion");
    if (languageVersion.equals("Polish")) {
      LANGUAGE_CONFIG = "Polish.properties";
    } else {
      LANGUAGE_CONFIG = "English.properties";
    }
    InputStream language = ClassLoader.getSystemResourceAsStream(LANGUAGE_CONFIG);
    try {
      properties.load(language);
    } catch (IOException e) {
      e.printStackTrace();
    }
    name = properties.getProperty("name");
    wait = properties.getProperty("wait");
    play = properties.getProperty("play");
    win = properties.getProperty("win");
    loss = properties.getProperty("loss");
    nameLabel = properties.getProperty("nameLabel");
    connect = properties.getProperty("connect");
  }

  public String getName() {
    return name;
  }

  public String getWait() {
    return wait;
  }

  public String getPlay() {
    return play;
  }

  public String getWin() {
    return win;
  }

  public String getLoss() {
    return loss;
  }

  public String getConnect() {
    return connect;
  }

  public String getNameLabel() {
    return nameLabel;
  }
}
