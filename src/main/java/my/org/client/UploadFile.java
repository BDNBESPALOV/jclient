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

    File filePatch=new File(patchAZKzip);

    Properties property = new Properties();




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
                /* обращение к properties */
                FileInputStream fis = new FileInputStream("Client.properties");

        ) {

            /*отправка для регистрации соединения с  Контроллером */
            System.out.println(" ответил clientAdminFile");
            out.println("clientAdminFile");
            /* записываем батовый массив полученный от Контроллера в файл */
            byteArray = new byte[8192];
            int in;
            while ((in = bis.read(byteArray)) != -1){
                bos.write(byteArray,0,in);
            }



            /* отправляем размер полученного файла */
            out.println("SizeFile:"+filePatch.length());



            /* определение пути к СП куда следует поместить файлы патча */
            property.load(fis);
            String homeSP = property.getProperty("sp.home");
            /* команда linux для копирования */
            String copyCommand = "cp -R -f -v ./"+patchAZK+"/* "+homeSP;
            /* выполение команды в болочке sh */
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",copyCommand} );
            /* мониторинг процесса копирования */
            BufferedReader inb = new BufferedReader(new InputStreamReader(process.getInputStream()));
            /* временная переменная для записли строки */
            String inputLine;
            /* временная переменная для подсчета итераций цикла */
            int quantityIteration = 0;
            /* в цикле считываем сообщения от оболочки linux */
            while ((inputLine = inb.readLine()) != null) {
                System.out.println(inputLine);
                quantityIteration++;
            }


            /* проверка что файлы копировались (в перспективе установить нармальную логику проверки )
            * и отправляем результат контроллеру  */
            if (quantityIteration > 100){
                out.println("copying: More than 100 files updated!");
            } else {
                out.println("copying: It seems there is a problem with copying !!!");
            }


            socket.close();
            System.out.println("Загрузка завершена...");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
