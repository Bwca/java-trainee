package storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public interface StorageService {
    public UUID storePage (InputStream stream) throws IOException;
    public InputStream readPage (UUID id) throws IOException;
}
