package storage;

/** Фабрика службы хранения/чтения веб-страниц. */
public class StorageServiceFactory {
    public static StorageService getStorageService() {
        return new FileService();
    }
}
