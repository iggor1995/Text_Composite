package kz.epam.java.text.operation;

/**
 * Created by User on 09.07.2017.
 */
import java.io.*;
import java.nio.charset.StandardCharsets;


public class FileReader {
    private static final String INPUTFILE = "Text.txt";
    private static final String NOCHAR = "";
    private static final int ENDFILE = -1;

    public String readFile() {

        String text = NOCHAR;
        try (Reader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(INPUTFILE), "Cp1251"))) {

            int i;
            while ((i = reader.read()) != ENDFILE) {
                text += (char) i;
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
