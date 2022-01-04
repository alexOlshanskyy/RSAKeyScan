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
                System.out.println("Getting keys for username: " + user);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    response.append("[ "); // need a space for easier split later

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        response.append(' ');
                    } in.close();
                    response.append(']');
                    String res = response.toString();
                    //System.out.println(res);
                    List<String> keys = formatString(res);
                    //data.add(new DataEntry(res, user, null));
                    for (String key : keys) {
                        data.add(new DataEntry("", user, ModulusExtractionUtil.parseSSHPublicKey(key)));
                    }
                } else {
                    System.out.println("GET Failed for user: " + user + " Error Code: " + responseCode);
                }
            } catch (Exception e) {
                System.out.println(e.toString()); // keep going
            }
        }
        return data;
    }


    private static List<String> formatString(String raw) {
        List<String> keys = new ArrayList<>();
        String[] split = raw.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("ssh-rsa")){
                keys.add(split[i] + " " + split[i+1]);
                i++;
            }
        }
        return keys;
    }
}
