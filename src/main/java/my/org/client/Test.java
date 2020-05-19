package my.org.client;

import java.io.*;
import java.util.Properties;

public class Test {

    public static void main(String [] args) throws IOException {

        Process p = Runtime.getRuntime().exec("/u02/1.40.0.285/1.40.0.285/SQL/DBUpdate.sh");

        InputStream in = p.getInputStream();
        OutputStream outputStream = p.getOutputStream();
        String line;
        BufferedReader inb = new BufferedReader(new InputStreamReader(in));




        String inputLine; /*в цикле считываем сообщения от сервера */
        while ((inputLine = inb.readLine()) != null) {

            if(inputLine.contains("Found")){
                System.out.println("FoundFoundFoundFound");

                line = "Y" + "\n";
                outputStream.write(line.getBytes());
                outputStream.flush();

            }

            System.out.println(inputLine);
        }



    }

}
