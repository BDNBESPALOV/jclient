package my.org.client;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class Test {

    public static void main(String [] args) throws IOException {
//        //патч AZK запокованный
//        String patchAZKzip = "/u02/client_GZ.zip";
//        //папка содержащия распакованный патч
//        String patchAZK = patchAZKzip.replaceAll(".zip(.*)","");
//
//        // определение пути к СП куда следует поместить файлы патча
//        FileInputStream fis = new FileInputStream("Client.properties");
//        Properties property = new Properties();
//        property.load(fis);
//        String homeSP = property.getProperty("sp.home");
//
//        //String copyCommand = "cp -R -f -v "+patchAZK+"/* "+homeSP;
//        String copyCommand = "/bin/cp -R -f -v /u02/GZ/* /u02/client_GZ";
//
//        System.out.println("patchAZK = "+patchAZK);
//        System.out.println("homeSP = "+homeSP);
//
//        //Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",copyCommand} );
//        Process process = Runtime.getRuntime().exec(new String[]{"cmd", "/c","dir"} );
//
//
//        BufferedReader inb = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//        String inputLine;
//        /*в цикле считываем сообщения от SP */
//        while ((inputLine = inb.readLine()) != null) {
//
//            /*отправлять все ответы от SP */
//            System.out.println(inputLine);
//        }

        String inputLine;
        String line ;

        // Process p = Runtime.getRuntime().exec(pathUpdate);

        Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c","type","order.log"});
       // Process p = Runtime.getRuntime().exec(new String[]{"cmd", "cd","c:/","&","type","pom.xml"});

        InputStream in = p.getInputStream();

        BufferedReader inb = new BufferedReader(new InputStreamReader(in,"UTF-8"));
       /// OutputStream outputStream = p.getOutputStream();

        /*в цикле считываем сообщения от SP */
        while ((inputLine = inb.readLine()) != null) {
            System.out.println("inputLine");
            System.out.println(inputLine);

        }
        in.close();
        inb.close();



    }

}
