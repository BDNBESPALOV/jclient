package my.org.client;

import my.org.client.md5.ApacheMd5;
import org.slf4j.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;


public class UploadFile {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UploadFile.class);
    //патч AZK запокованный
    //String patchAZKzip = "D:\\TEMP\\file.zip";
    String patchAZKzip = "tempPatchFolder/patch.zip";
    //папка содержащия распакованный патч
    String patchAZK = patchAZKzip.replaceAll(".zip(.*)","");

    File filePatch=new File(patchAZKzip);

    Properties property = new Properties();

    MessageDigest md = MessageDigest.getInstance("MD5");

    int fileSize;




    byte  [] byteArray;
    public UploadFile(Socket socket) throws NoSuchAlgorithmException {
        File tempFolder = new File("tempPatchFolder");
        if (!tempFolder.exists()){
            tempFolder.mkdir();
        }



        try(
                /* для получения размера  */
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

                 BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
//                InputStream is = socket.getInputStream();
//                DigestInputStream dis = new DigestInputStream(is, md);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("tempPatchFolder/patch.zip"));

                /* обращение к properties */
                FileInputStream fis = new FileInputStream("Client.properties");

        ) {


            log.info(socket.isBound()+" <-bound "+socket.isClosed()+" <-close "
             +socket.isConnected()+" <-connect "+socket.isInputShutdown()+" <-isShutdown");
            /*отправка для регистрации соединения с  Контроллером */
            log.info(" ответил clientAdminFile");

            out.println("clientAdminFile");

            log.info(socket.isBound()+" <-bound "+socket.isClosed()+" <-close "
                    +socket.isConnected()+" <-connect "+socket.isInputShutdown()+" <-isShutdown");

            fileSize = dataInputStream.readInt();
            log.info("size "+fileSize);
            /* записываем батовый массив полученный от Контроллера в файл */
            byteArray = new byte[8192];
            int in;
            int sizeOutFile = 0;
            log.info("Начало записи файла");
            while ((in = bis.read(byteArray)) != -1){
                bos.write(byteArray,0,in);
                bos.flush();
                log.info("Загружено "+(sizeOutFile+=in));
                if (sizeOutFile == fileSize){
                    log.info("MD5:"+ApacheMd5.md5(filePatch.getPath()));
                    out.println("MD5:"+ ApacheMd5.md5(filePatch.getPath()));
                    log.info("Файл загружен");
                    break;
                }
            }
            bis.close();
            bos.close();
            log.info("Файл загружен");

            log.info(socket.isBound()+" <-bound "+socket.isClosed()+" <-close "
                    +socket.isConnected()+" <-connect "+socket.isInputShutdown()+" <-isShutdown");

//            /* отправляем размер полученного файла */
//            out.println("SizeFile:"+filePatch.length());
            log.info("MD5:"+ApacheMd5.md5(filePatch.getPath()));
            out.println("SizeFile:"+ ApacheMd5.md5(filePatch.getPath()));

            log.info(socket.isBound()+" <-bound "+socket.isClosed()+" <-close "
                    +socket.isConnected()+" <-connect "+socket.isInputShutdown()+" <-isShutdown");

            /* распаковка  */
            //****

            /* переопределяем путь (поле реализации распаковки убрать ) */
            patchAZK = "./tempPatchFolder";


                                    /* Linux */
//            /* определение пути к СП куда следует поместить файлы патча */
//            property.load(fis);
//            String homeSP = property.getProperty("sp.home");
//            log.info("Property sp.home "+homeSP);
//            /* команда linux для копирования */
//            String copyCommand = "cp -R -f -v ./"+patchAZK+"/* "+homeSP;
//            log.info("copyCommand "+copyCommand);
//            /* выполение команды в болочке sh */
//            Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash","-c",copyCommand} );
//            /* мониторинг процесса копирования */
//            BufferedReader inb = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            /* временная переменная для записли строки */
//            String inputLine;
//            /* временная переменная для подсчета итераций цикла */
//            int quantityIteration = 0;
//            /* в цикле считываем сообщения от оболочки linux */
//            while ((inputLine = inb.readLine()) != null) {
//                log.info(inputLine);
//                quantityIteration++;
//            }
            /* Linux */  /* Linux */ /* Linux */ /* Linux */ /* Linux */




            socket.close();
            log.info("Загрузка завершена...");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
