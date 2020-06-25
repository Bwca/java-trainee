package bwca;

import api.ApiService;
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
    private ApiService apiservice;
    private StorageService storageService;
    private Input input;
    private ParserService parserService;

    public App() {
        this.apiservice = new ApiService();
        this.storageService = StorageServiceFactory.getStorageService();
        this.input = InputServiceFactory.getInputService();
        this.parserService = new ParserService();
    }

    public static void main( String[] args ) {
        App app = new App();
        try {
            app.saveWebPage();
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  ApiService apiservice = new ApiService();
        StorageService storageService = new FileService();
        Input input = new InputService();
        ParserService parserService = new ParserService();

        String url = input.getUrl();

        InputStream requestedPageStream = null;
        Scanner scanner = null;

        try {
            requestedPageStream = apiservice.getPage(url);
            UUID pageId = storageService.storePage( requestedPageStream );
            requestedPageStream.close();

            InputStream savedPage = storageService.readPage(pageId);
            scanner = new Scanner(savedPage, "UTF-8");

            Map<String, Integer> wordOccurrences = new HashMap<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matches = parserService.matchAllWords(line);
                while (matches.find()) {
                    String word = matches.group();
                    Integer currentWordCount = wordOccurrences.get(word);
                    wordOccurrences.put(word, currentWordCount != null ? currentWordCount + 1 : 1);
                }
            }

            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(scanner != null){
                scanner.close();
            }
        }*/
    }

    private UUID saveWebPage() throws IOException {
        String url = this.input.getUrl();
        InputStream requestedPageStream = null;
        requestedPageStream = this.apiservice.getPage(url);
        UUID pageId = this.storageService.storePage( requestedPageStream );
        requestedPageStream.close();
        return pageId;
    }
}
