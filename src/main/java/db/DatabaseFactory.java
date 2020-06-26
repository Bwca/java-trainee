package db;

/** Фабрика конкретной бд. */
public class DatabaseFactory {
    public static Database getDatabaseService() {
        return new InMemoryDbService();
    }
}
