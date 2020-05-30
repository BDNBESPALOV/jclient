package my.org.client;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Properties;


public class UploadFile {
    //патч AZK запокованный
    String patchAZKzip = "D:\\TEMP\\file.zip";
    //папка содержащия распакованный патч
    String patchAZK = patchAZKzip.replaceAll(".zip(.*)","");

    File f=new File(patchAZKzip);


    byte  [] byteArray;
    UploadFile(Socket socket)  {
        File tempFolder = new File("tempPatchFolder");
        if (!tempFolder.exists()){
            tempFolder.mkdir();
        }



        try(

                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("tempPatchFolder/path.zip"));

        ) {

            /*отправка для регистрации соединения с  Контроллером */
            System.out.println(" ответил clientAdminFile");
            out.println("clientAdminFile");

            byteArray = new byte[8192];
            int in;
            while ((in = bis.read(byteArray)) != -1){
                System.out.println("."+new Date());
                bos.write(byteArray,0,in);
            }

            socket.close();

            // определение пути к СП куда следует поместить файлы патча
            FileInputStream fis = new FileInputStream("Client.properties");
            Properties property = new Properties();
            property.load(fis);
            String homeSP = property.getProperty("sp.home");

            String copyCommand = "cp -R -f -v ./"+patchAZK+"/* "+homeSP;

            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",copyCommand} );

            //мониторинг процесса копирования
            BufferedReader inb = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String inputLine;
            /*в цикле считываем сообщения от оболочки linux */
            while ((inputLine = inb.readLine()) != null) {
                System.out.println(inputLine);
            }

            System.out.println(" загрузка завершена ");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
