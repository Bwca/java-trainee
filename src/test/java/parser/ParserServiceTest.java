package parser;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ParserServiceTest {

    @Test
    public void shouldExtractWordsFromHtml() {
        // Arrange
        String htmlFragment = "<!-- текст комментария --><p>Сообщение внутри тегов</p>";
        ParserService parserService = new ParserService();

        // Act
        List<String> words = Arrays.asList(parserService.getWordsFromHtmlFragment(htmlFragment));

        // Assert
        assertEquals(3, words.size());
        assertTrue(words.contains("Сообщение"));
        assertTrue(words.contains("внутри"));
        assertTrue(words.contains("тегов"));
        assertFalse(words.contains("текст"));
        assertFalse(words.contains("комментария"));
    }
}
