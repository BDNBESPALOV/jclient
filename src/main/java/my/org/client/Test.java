package my.org.client;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.zip.ZipEntry;


public class Test {

    public static void main(String [] args) throws IOException, URISyntaxException {
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

//        File f = new File("A:\\temp\\patch.zip");
//        System.out.println(f.exists());
//        System.out.println(f.isFile());
//        System.out.println(f.canRead());
//        System.out.println(f.canExecute());
//        System.out.println(f.canWrite());

//      String outFile = "A:\\temp\\patch.zip.waitLoading";
//        System.out.println("A:\\temp\\patch.zip".length());
//        System.out.println(outFile);
//
//outFile = outFile.substring(0,17);
//        System.out.println(outFile);
       File file = new File("A:\\temp\\patch.zip"+".waitLoading");
//        File file2 = new File("A:\\temp\\patch.zip.waitLoading".substring(0, 17));
//        //new File(outFile.substring(0, 17)))
        System.out.println(file.length());

//          String login = "f_belgorod";
//         String password = "E666T5fZ";
//        Authenticator.setDefault(new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(login, password.toCharArray());
//            }
//        });
//        URL uri = new URL("https://bftcloud.bftcom.com/remote.php/webdav/belgorodskaya_obl/belgorodskaya_obl/!gz/!other/order.log.zip");
////        File file1 = new File(uri);
////        System.out.println(file1.exists());
//
//        URL url = new URL("https://bftcloud.bftcom.com/remote.php/webdav/!azk_update/!gz/!builds/!1.40.0/patch_1.40.0.293_p3_20200508.zip");
//        File file3 = new File("https://bftcloud.bftcom.com/remote.php/webdav/!azk_update/!gz/!builds/!1.40.0/patch_1.40.0.293_p3_20200508.zip");
//
//        URLConnection connection = uri.openConnection();
//
//
//        System.out.println(connection.getContentLength());
//        System.out.println(file3.exists());
    }

}
