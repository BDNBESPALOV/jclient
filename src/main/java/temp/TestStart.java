package temp;


import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class TestStart {
    private Socket socket = null;
    private int timeOut;
    private final String MY_NAME;
    private final String IP;
    private final int PORT;


    public TestStart(String name, String IP, int port, int timeOut){
        this.IP = IP;
        this.PORT = port;
        this.MY_NAME = name;
        this.timeOut = timeOut;
    }

    /* дожидается доступности сервера и отправляет ему сообщение  */
    public void sendHello ()  {
        while (!connectServer()){
            try {
                System.out.println("Сервер неноступн, ждем "+ timeOut +" сек...");
                Thread.sleep(timeOut * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connectServer();
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream())), true)){
            out.print(MY_NAME);
            System.out.println("отправили "+ MY_NAME);
        } catch (IOException e) {

        }



    }
    /* производит  нициализацию переменной socket
    если сервер доступен возвращает true иначе false */
    private boolean connectServer(){
        try {
            socket =  new Socket(IP, PORT);
        } catch (ConnectException e) {
            return  false;
        } catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    private void messageProcessing(String msg){

    }


    public static void main(String[] args){
        new TestStart("","127.0.0.1",8181,2).sendHello();
    }



}
