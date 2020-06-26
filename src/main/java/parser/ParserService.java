package parser;

import java.util.Arrays;

/** Служба для парсинга HTML. */
public class ParserService {

    /** Регулярное выражение для проверки слов. */
    private final String wordMatch = "(?!-)[ёЁа-яА-Я\\-]+";

    /** Регулярное выражение для фрагментирования строки на потенциальные слова. */
    private final String delimiters = "[\\s,.!?\";:\\[\\]()\r\t»«]";

    /** Получить слова из фрагмента HTML. */
    public String[] getWordsFromHtmlFragment(String html) {
        String [] textChunks = html.replaceAll("<[^>]*>", "").split(this.delimiters);
        return Arrays.stream(textChunks).filter(s -> s.matches(this.wordMatch)).toArray(String[]::new);
    }
}
