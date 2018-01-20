package mypackage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlReader {

    public static void getFile(String adressFrom, String adressIn) {
        URL url;
        File file = null;
        try {
            // Define your URL
            url = new URL(adressFrom);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //Save text file (HTML Source)
            String fileName = adressIn;

            file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            //use FileWriter to write file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
            }

            bw.close();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





