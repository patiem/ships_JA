package file;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class FileWriterTest {
    private static final String MSG = "veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeery loooooooooooooooooooooooooooooooooooooooooong msg";

    @AfterTest
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get("transcript.txt"));
    }

    public void writeToFile() throws IOException {
        //when
        FileWriter.write(MSG);
        //then
        String output = new String(Files.readAllBytes(Paths.get("transcript.txt")));
        assertThat(output).isNotEmpty();
    }
}