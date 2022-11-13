package util;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;

public class FileUtilsTest {

    @Test
    public void testFileAppend() throws IOException, URISyntaxException {
        FileUtils.addWordToFile("newTestWord", "words.txt");
        BufferedReader reader = FileUtils.getReader("words.txt");
        String line;
        HashSet<String> words = new HashSet<>();
        while (reader.ready()) {
            line = reader.readLine();
            words.add(line);
        }
        reader.close();

        Assert.assertTrue(words.contains("newTestWord"));
    }
}
