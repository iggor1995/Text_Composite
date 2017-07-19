package kz.epam.java.text.split;

import kz.epam.java.text.entity.Component;
import kz.epam.java.text.entity.Composite;
import kz.epam.java.text.entity.Symbol;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TSplitter {

    private static final char SPACESYM = ' ';
    private static final String PATTERN_SYMBOLS = "[\\,\\.\\;\\:\\-\\?\\!]";
    private static final String NEWLINE = "\\n";
    private static final char ENTER1 = '\r';
    private static final char ENTER2 = '\n';
    private static final String SPLIT_INTO_SENTENCES_PATTERN = "[ \\w+\\,\\'\\-\\:\\(\\)\\«\\»\\;\\—\\№]+[\\.\\?\\!]";
    private static final String PATTERN_WORDS_PLUS_SYMBOLS = "(\\w+\\-?\'?\\w+ \\()" +
            "|(\\w+\\-?\'?\\w+\\)\\, )|" +
            "(\\w+\\-?\\w+\\)|" +
            "(\\w+\\-?\\w+\\, )" +
            "|(\\w+\\-?\\w+\\; )|" +
            "(\\w+\\-?\\w+\\: )" +
            "|(\\w+\\-?\\w+ )" +
            "|(\\w+\\-?\\w+\\. ?)|" +
            "(\\w+\\-?\\w+\\? ?)|" +
            "(\\w+\\-?\\w+\\! ?)|" +
            "(\\w ))";
    private static final String PATTERN_WORDS = "(\\w+)|(\\w)";
    private static final String SPLIT_INTO_SYMBOLS_PATTERN = "";

    public Component splitText(String text){
        Component textComposite = new Composite();
        splitTextIntoParagraphs(text, textComposite);
        return textComposite;
    }

    private void splitTextIntoParagraphs(String text, Component textComposite){
        String[] paragraphs = text.split(NEWLINE);

        for(int i = 0; i < paragraphs.length; i++){
            if(paragraphs[i].length() == 1){
                addEnter(textComposite);
            }
            else{
                Component paragraphComposite = new Composite();
                splitParagraphsIntoSentences(paragraphs[i], paragraphComposite);
                textComposite.addComponent(paragraphComposite);
                if(i != paragraphs.length - 1)
                addEnter(textComposite);
            }

        }
    }
    private void splitParagraphsIntoSentences(String parText, Component paragraphComposite){

        Pattern patSentences = Pattern.compile(SPLIT_INTO_SENTENCES_PATTERN, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matchSentence = patSentences.matcher(parText);

        while(matchSentence.find()){
                Component sentenceComposite = new Composite();
                splitSentencesIntoWords(matchSentence.group(), sentenceComposite);
                paragraphComposite.addComponent(sentenceComposite);
            }
        }



    private void splitSentencesIntoWords(String sentenceText, Component setntenceComposite){

        Pattern patWordsAndSymbols = Pattern.compile(PATTERN_WORDS_PLUS_SYMBOLS, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern patWords = Pattern.compile(PATTERN_WORDS, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern patSymbols = Pattern.compile(PATTERN_SYMBOLS, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matchWordsAndSymbols = patWordsAndSymbols.matcher(sentenceText);

        while(matchWordsAndSymbols.find()){
            Matcher matcherWords = patWords.matcher(matchWordsAndSymbols.group());
            if (matcherWords.find()){
                if(matcherWords.group().length() == 1){
                    setntenceComposite.addComponent(new Symbol(matcherWords.group().charAt(0)));
                }
                else {
                    Component wordComposite = new Composite();
                    splitWordIntoSymbols(matcherWords.group(), wordComposite);
                    setntenceComposite.addComponent(wordComposite);
                }
            }
            Matcher matchSymbols = patSymbols.matcher(matchWordsAndSymbols.group());

            if(matchSymbols.find()){
                char sentenceSymbol = matchSymbols.group().charAt(0);
                setntenceComposite.addComponent(new Symbol(sentenceSymbol));
                addSpace(setntenceComposite);
            }
            else addSpace(setntenceComposite);
        }
    }

    private void splitWordIntoSymbols(String wordText, Component wordComposite){
        String[] symbols = wordText.split(SPLIT_INTO_SYMBOLS_PATTERN);
        for(String symb : symbols){
            char wordSymbol = symb.charAt(0);
            Component symbol = new Symbol(wordSymbol);
            wordComposite.addComponent(symbol);
        }
    }
    private void addSpace(Component parComposite){
        char space = SPACESYM;
        parComposite.addComponent(new Symbol(space));
    }
    private void addEnter(Component textComposite){
        char er = ENTER1;
        char en = ENTER2;
        textComposite.addComponent(new Symbol(er));
        textComposite.addComponent(new Symbol(en));
    }
}
