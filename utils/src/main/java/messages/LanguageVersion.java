package messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageVersion {

  private static final Logger LOGGER = Logger.getLogger(LanguageVersion.class.getName());
  private Properties properties;

  public LanguageVersion() {
    setLanguage();
  }

  private void setLanguage() {
    String serverConfigFile = "config.properties";
    String languageConfig;
    properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(serverConfigFile);
    try {
      properties.load(config);
      String languageVersion = properties.getProperty("languageVersion");
      
      if (languageVersion.equals("Polish")) {
        languageConfig = "Polish.properties";
      } else {
        languageConfig = "English.properties";
      }
      InputStream language = ClassLoader.getSystemResourceAsStream(languageConfig);
      properties.load(language);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,e.getMessage());
    }
  }

  public String getMessage(String message) {
    return properties.getProperty(message);
  }
}
