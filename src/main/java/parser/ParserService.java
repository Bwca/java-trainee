package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserService { //ёа-я\\-
    private final Pattern searchPattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);

    public Matcher matchAllWords(String line){
        return this.searchPattern.matcher(line);
    }
}
