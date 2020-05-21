package my.org.client;


import java.io.*;
import java.net.Socket;

public class UploadFile {

    UploadFile(Socket socket){
        try(
                Socket socket2 = socket;
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true)
        ) {

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
