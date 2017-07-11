package kz.epam.java.text.runner;

 import kz.epam.java.text.entity.Text;
 import kz.epam.java.text.filtration.Filter;
 import kz.epam.java.text.operation.FileReader;
 import kz.epam.java.text.operation.FWriter;
 import kz.epam.java.text.split.TSplitter;

public class Runner {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
        FWriter fWriter = new FWriter();
        TSplitter tSplitter = new TSplitter();

        String tText = reader.readFile();
        Text text = new Text(tSplitter.splitText(tText));
        fWriter.writeFile(text);

        Filter filter = new Filter();
        filter.printFilteredText(text.getComposite());

    }
 }