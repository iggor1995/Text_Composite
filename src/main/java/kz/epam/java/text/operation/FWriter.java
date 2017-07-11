package kz.epam.java.text.operation;

/**
 * Created by User on 09.07.2017.
 */
import kz.epam.java.text.entity.Text;
import java.io.*;

public class FWriter {
    private static final String OUTPUTFILE = "newFile.txt";

    public  void writeFile(Text text) {
        try (Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(OUTPUTFILE),"Cp1251"))) {
            fileWriter.write(text.toString());
            fileWriter.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
