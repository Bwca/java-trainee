package bwca;

import api.ApiService;
import db.Database;
import db.DatabaseFactory;
import input.Input;
import input.InputServiceFactory;
import parser.ParserService;
import storage.StorageService;
import storage.StorageServiceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class App 
{
    private final ApiService apiservice;
    private final StorageService storageService;
    private final Input input;
    private final ParserService parserService;
    private final Database databaseService;

    private static App INSTANCE;

    public static App getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new App();
        }
        return INSTANCE;
    }

    private App() {
        this.apiservice = new ApiService();
        this.storageService = StorageServiceFactory.getStorageService();
        this.input = InputServiceFactory.getInputService();
        this.parserService = new ParserService();
        this.databaseService = DatabaseFactory.getDatabaseService();
    }

    public static void main( String[] args ) {
        App app = getInstance();
        do{
            app.requestPageAndSaveWordOccurrences();
        } while(app.mustContinue());
    }

    private boolean mustContinue() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Продолжить? (y/n)");
            String answer = scanner.nextLine();
            if(answer.matches("(?i)[yn]")){
                return answer.matches("(?i)y");
            }
        }

    }

    private void requestPageAndSaveWordOccurrences() {
        try {
            String url = this.input.getUrl();
            UUID storedPageId = this.saveWebPage(url);
            InputStream pageStream = this.storageService.readPage(storedPageId);
            Map<String, Integer> wordOccurrences = this.countWordOccurrencesInStoredWebPage(pageStream);
            pageStream.close();
            System.out.println(wordOccurrences);
            int recordIndex = this.databaseService.saveWordsOccurrences(storedPageId, wordOccurrences);
            System.out.println("Запись сохранена с id " + recordIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Подсчет слов в сохраненной странице.
     * @param savedPage сохраненная страница
     * */
    private Map<String, Integer> countWordOccurrencesInStoredWebPage(InputStream savedPage) throws IOException {
        Scanner scanner = new Scanner(savedPage, "UTF-8");
        Map<String, Integer> wordOccurrences = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String [] words = this.parserService.getWordsFromHtmlFragment(line);

            for (String word : words) {
                String currentWord = word.toUpperCase();
                Integer currentWordCount = wordOccurrences.get(currentWord);
                Integer newWordCount = currentWordCount != null ? currentWordCount + 1 : 1;
                wordOccurrences.put(currentWord, newWordCount);
            }
        }

        if (scanner.ioException() != null) {
            throw scanner.ioException();
        }

        scanner.close();

        return wordOccurrences;
    }

    /** Сохранение веб-страницы.
     * @param url адрес веб-страницы, которую необходимо сохранить.
     * */
    private UUID saveWebPage(String url) throws IOException {
        InputStream requestedPageStream;
        requestedPageStream = this.apiservice.getPage(url);
        UUID pageId = this.storageService.storePage( requestedPageStream );
        requestedPageStream.close();
        return pageId;
    }
}
