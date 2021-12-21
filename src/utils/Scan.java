package utils;

import model.DataEntry;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Scan {

    private static final String URL_BASE = "https://github.com/";
    private static final String FILE_TYPE = ".keys";

    public static List<DataEntry> start(List<String> userNames) {
        List<DataEntry> data = new ArrayList<>();
        for (String user : userNames) {
            try {
                URL url = new URL(URL_BASE + user + FILE_TYPE);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        response.append('\n');
                    } in.close();

                    // raw response
                    System.out.println(response.toString());
                    data.add(new DataEntry(response.toString()));
                } else {
                    System.out.println("GET Failed");
                }
            } catch (Exception e) {
                System.out.println(e); // keep going
            }
        }
        return data;
    }
}
