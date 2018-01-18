package gui;

import gui.starting.StartBoard;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It starts the gui.
 *
 * @version 1.5
 */
class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
  private static final String SERVER_CONFIG_FILE = "config.properties";

  public static void main(String[] args) {
    try {
      if (isServerConfigValid()) {
        StartBoard.run(args);
      } else {
        LOGGER.log(Level.INFO, "Closing the game as the IP was not valid or port is invalid");
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }
  }

  private static boolean isServerConfigValid() throws IOException {

    String IP_ADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    Properties properties = new Properties();
    InputStream config = ClassLoader.getSystemResourceAsStream(SERVER_CONFIG_FILE);
    properties.load(config);
    String retrievedIP = properties.getProperty("IP");
    int retrievedPort = Integer.parseInt(properties.getProperty("portNumber"));

    Pattern pattern = Pattern.compile(IP_ADDRESS_PATTERN);
    Matcher matcher = pattern.matcher(retrievedIP);

    Pattern patternLocal = Pattern.compile("localhost", Pattern.CASE_INSENSITIVE);
    Matcher matcherLocal = patternLocal.matcher(retrievedIP);

    return (matcher.matches() || matcherLocal.matches()) && isPortValid(retrievedPort);
  }

  private static boolean isPortValid(int portNumber) {
    return portNumber > 1023 && portNumber < 65537;
  }

}
