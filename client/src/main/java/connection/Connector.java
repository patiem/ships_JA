package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Connector {
    OutputStream getOutStream() throws IOException;
    InputStream getInStream() throws IOException;
}
