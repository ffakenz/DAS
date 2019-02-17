package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class MockUtils {
    public static String readMockBodyFromFile(final String fileName) {
        final InputStream is = MockUtils.class.getClassLoader().getResourceAsStream(fileName);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch(final IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
