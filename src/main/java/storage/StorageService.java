package storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/** Интерфейс службы для сохранения веб-страницы. */
public interface StorageService {
    /** Сохранить страницу, вернуть ее UUID. */
    UUID storePage(InputStream stream) throws IOException;
    /** Прочитать страницу по ее UUID. */
    InputStream readPage(UUID id) throws IOException;
}
