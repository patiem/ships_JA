package file;

import messages.ClientLogger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;

public class FileWriter {
    private static final String TRANSCRIPT = "transcript.txt";

    private FileWriter() {
    }

    public static void write(String message) {
        try (PrintWriter out = new PrintWriter(TRANSCRIPT)) {
            out.println(message);
        } catch (FileNotFoundException e) {
            ClientLogger.getInstance().log(Level.SEVERE, e.getMessage());
        }
    }
}
