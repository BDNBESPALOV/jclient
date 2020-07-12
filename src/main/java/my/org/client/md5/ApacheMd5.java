package my.org.client.md5;

import javafx.scene.shape.Path;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApacheMd5 {

    public static String md5(String file){
    //9e387b763c18e1d0342376cee818db3b
        String m = "-1";
        try {
            m = DigestUtils.md5Hex(Files.newInputStream(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
    //  System.out.println(ApacheMd5.md5("A:\\temp\\patch.zip"));
//        System.out.println(ApacheMd5.md5("A:\\temp\\patch.zip"));
//        System.out.println(ApacheMd5.md5("A:\\temp\\file.zip"));
      //  System.out.println(ApacheMd5.md5("A:\\JAVAPROJEСTS\\jclinet\\patch.zip"));

        /* записываем батовый массив полученный от Контроллера в файл
        * 6e10ae290f17cc8143b51929c6291a8b
            0fc8d6a1051cfb5f374ca7cefb0ed10c
        * */

     //   BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("A:\\temp\\patch.zip")));
//****
        MessageDigest md = MessageDigest.getInstance("MD5");
        try(
                InputStream is = Files.newInputStream(Paths.get("A:\\temp\\patch.zip"));
                DigestInputStream dis = new DigestInputStream(is, md);

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./patch.zip"));
        ) {
            byte[] byteArray = new byte[8192];
            int in;
            int sizeOutFile = 0;
            while ((in = dis.read(byteArray)) != -1) {
                bos.write(byteArray, 0, in);
                // log.info("Загружено "+(sizeOutFile+=in));
            }
        } catch (IOException e){}


          System.out.println(ApacheMd5.md5("A:\\temp\\patch.zip"));
          System.out.println(ApacheMd5.md5("A:\\JAVAPROJEСTS\\jclinet\\patch.zip"));

    }
}
