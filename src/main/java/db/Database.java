package db;

import java.util.Map;
import java.util.UUID;

/** Интерфейс взаимодействия с базой данных. */
public interface Database {
    /**
     * Сохранение данных о количестве слов.
     *
     * @param pageId           идентификатор сохраненной страницы.
     * @param wordsOccurrences данные о количестве найденных слов.
     */
    default int saveWordsOccurrences(UUID pageId, Map<String, Integer> wordsOccurrences) {
        return 0;
    }
}
