package storage;

public class StorageServiceFactory {
    public static StorageService getStorageService() {
        return new FileService();
    }
}
