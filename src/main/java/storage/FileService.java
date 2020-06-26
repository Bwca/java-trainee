package storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/** Служба для сохранения веб-страниц на диск и их последующего чтения. */
public class FileService implements StorageService {

    /** Адрес каталога для сохранения страниц. */
    private final String storageFolder = "./saved-pages/";

    @Override
    public InputStream readPage(UUID id) throws FileNotFoundException {
        return new FileInputStream(this.storageFolder + id.toString());
    }

    @Override
    public UUID storePage(InputStream inputStream) throws IOException {
        File storageDir = new File(this.storageFolder);
        if (!storageDir.exists()){
            if(!storageDir.mkdir()){
                throw new IOException("Ошибка при попытке создания директории для сохранения страниц!");
            }
        }

        UUID id = UUID.randomUUID();
        String filePath = storageDir + id.toString();
        File targetFile = new File(filePath);
        if(!targetFile.createNewFile()){
            throw new IOException("Неудалось сохранить файл!");
        }
        Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return id;
    }
}
