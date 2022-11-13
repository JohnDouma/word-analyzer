package util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Methods for accessing files
 */
public class FileUtils {

    /**
     * Get <code>BufferedReader</code> for given filename
     * @param filename name of file to read
     * @return BufferedReader if file exists, null otherwise
     */
    public static BufferedReader getReader(String filename) {
        if (filename == null || filename.isEmpty()) {
            return null;
        }

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
        if (is == null) {
            return null;
        }

        InputStreamReader reader = new InputStreamReader(is);
        return new BufferedReader(reader);
    }

    /**
     * Appends the word to the end of the file given by filename.
     *
     * @param word String to be appended to the file
     * @param filename name of the file
     *
     */
    public static void addWordToFile(String word, String filename) throws URISyntaxException, FileNotFoundException {
        URL url = ClassLoader.getSystemClassLoader().getResource(filename);
        if (url == null) {
            throw new FileNotFoundException(filename + " doesn't exist");
        }

        File file = new File(url.toURI()).getAbsoluteFile();
        OutputStream os = new FileOutputStream(file, true);
        PrintWriter writer = new PrintWriter(os);
        writer.write(word + "\n");
        writer.flush();
        writer.close();
    }
}
