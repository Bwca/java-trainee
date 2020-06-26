package db;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void shouldSaveResults() {
        // Arrange
        Database db = DatabaseFactory.getDatabaseService();
        UUID id = UUID.randomUUID();
        Map<String, Integer> payload = new HashMap<>();
        payload.put("Test Word", 100);

        // Act
        int resultOne = db.saveWordsOccurrences(id, payload);
        int resultTwo = db.saveWordsOccurrences(id, payload);

        // Assert
        assertNotEquals(resultOne, resultTwo);
        assertEquals(1, resultOne);
        assertEquals(2, resultTwo);
    }
}
