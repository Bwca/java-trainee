package input;

import java.util.Scanner;

/** Служба для получения URL-адреса из пользовательского ввода. */
public class InputService implements Input {
    @Override
    public String getUrl() {
        Scanner scanner = new Scanner(System.in);
        String url;

        final String urlRegex = "https?://([\\w\\d]+)((\\.[\\w\\d]+)+)?/?";

        while(true) {
            System.out.println("Введите адрес страницы");
            url = scanner.next();

            if(url.matches(urlRegex)){
                break;
            }
            else{
                System.out.println("Неверный URL-адрес :(");
            }
        }

        return url;
    }
}
