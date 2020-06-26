package input;

/** Фабрика службы для получения URL-адреса. */
public class InputServiceFactory {
    public static Input getInputService() {
        return new InputService();
    }
}
