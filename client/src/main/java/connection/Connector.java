package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

interface Connector {
  OutputStream getOutStream() throws IOException;

  InputStream getInStream() throws IOException;
}
