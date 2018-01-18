package gui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HostValidator {

  private HostValidator() {
  }

  private static final Logger LOGGER = Logger.getLogger(HostValidator.class.getName());

  static boolean isServerConfigValid(String serverConfigFile) throws IOException {

    String ipPattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    Properties properties = new Properties();
    InputStream config = new FileInputStream(serverConfigFile);
    properties.load(config);

    String retrievedIP = properties.getProperty("IP");
    int retrievedPort = Integer.parseInt(properties.getProperty("portNumber"));

    Pattern pattern = Pattern.compile(ipPattern);
    Matcher matcher = pattern.matcher(retrievedIP);

    Pattern patternLocal = Pattern.compile("localhost", Pattern.CASE_INSENSITIVE);
    Matcher matcherLocal = patternLocal.matcher(retrievedIP);

    boolean isIpValid = matcher.matches() || matcherLocal.matches();
    if(!isIpValid) {
      LOGGER.log(Level.WARNING, "Closing the game as the host IP is invalid");
    }
    return isIpValid && isPortValid(retrievedPort);
  }

  private static boolean isPortValid(int portNumber) {
    int minPort = 1023;
    int maxPort = 65537;
    boolean isPortValid = portNumber > minPort && portNumber < maxPort;
    if (!isPortValid) {
      LOGGER.log(Level.WARNING, "Closing the game as the PORT is invalid");
    }
    return isPortValid;
  }
}
