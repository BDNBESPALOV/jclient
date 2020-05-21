package my.org.client;


import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class UploadFile {
    File f=new File("D:\\TEMP\\file.zip");
    Socket clientSocket;

    UploadFile(Socket socket) throws FileNotFoundException {
        this.clientSocket = socket;
        try(
                InputStream in = socket.getInputStream();
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);


                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream(), 8192)

        ) {

            /*отправка для регистрации соединения с  Контроллером */
            System.out.println(" ответил clientAdminFile");
            out.println("clientAdminFile");



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void recieveFile(String filename) {
        try {
            System.out.println("Start");
//            int s;
//            s = Integer.parseInt(in.readLine());
            byte[] byteArray = new byte[8192];
            BufferedInputStream bis = new BufferedInputStream(clientSocket.getInputStream(), 8192*100);
            File f = new File(filename);
            f.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
            int i;

            while (s > 0) {
                i = bis.read(byteArray);
                bos.write(byteArray, 0, i);
                s--;
            }
            bos.close();
            System.out.println("321");
        } catch (IOException e) {
            System.err.println("Recieve IO Error");
        }

        System.out.println("Recieved");
    }

}
