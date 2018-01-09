package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * It exposes methods for getting access to Input- and OutputStream.
 *
 * @author Patrycja Mikulska
 * @version 1.5
 */
interface Connector {
  OutputStream getOutStream() throws IOException;

  InputStream getInStream() throws IOException;

}
