package api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** Служба для осуществления http-запросов. */
public class ApiService {

    /** @param urlString запрашиваемый адрес веб страницы */
    public InputStream getPage(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        if(con.getResponseCode() != 200){
            throw new IOException("Ошибка при запросе веб-страницы!");
        }
        return con.getInputStream();
    }
}
