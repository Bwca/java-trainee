package db;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** БД в памяти */
public class InMemoryDbService implements Database {

    private Integer index = 1;

    private final Map<Integer, MemoryRecord> db = new HashMap<>();

    /** Схема записи в бд. */
    private class MemoryRecord {
        private final UUID pageId;
        private final Map<String, Integer> wordsOccurrences;
        private final Integer id;


        private MemoryRecord(UUID pageId, Map<String, Integer> wordsOccurrences, Integer id) {
            this.pageId = pageId;
            this.wordsOccurrences = wordsOccurrences;
            this.id = id;
        }
    }

    @Override
    public int saveWordsOccurrences(UUID pageId, Map<String, Integer> wordsOccurrences) {
        MemoryRecord record = new MemoryRecord(pageId, wordsOccurrences, this.index);
        this.db.put(this.index, record);
        return this.index++;
    }
}
