package storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileService implements StorageService {

    private final String storageFolder = "src/main/resources/";

    @Override
    public InputStream readPage(UUID id) throws FileNotFoundException {
        return new FileInputStream(this.storageFolder + id.toString());
    }

    @Override
    public UUID storePage(InputStream inputStream) throws IOException {
        UUID id = UUID.randomUUID();
        String filePath = this.storageFolder + id.toString();
        File targetFile = new File(filePath);
        targetFile.createNewFile();
        Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return id;
    }
}
