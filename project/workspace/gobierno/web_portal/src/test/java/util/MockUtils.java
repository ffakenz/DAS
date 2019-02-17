package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class MockUtils {
    public static String readMockBodyFromFile(String fileName) {
        InputStream is = MockUtils.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}
