package execution_JSON;

import my.org.client.UploadFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class AcceptFile implements Exec{


    @Override
    public void doProcess() {
        Properties property = new Properties();
        int serverPort = Integer.parseInt(property.getProperty("server.port"));
        String serverIP = property.getProperty("server.ip");
        try {
            new UploadFile(initSocket(serverIP,serverPort));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doProcess(int i) {

    }

    @Override
    public void doProcess(String str) {

    }

    @Override
    public void doProcess(PrintWriter out) {

    }

    private Socket initSocket(String serverIP, int serverPort){
        Socket socket = null;
        try {
            socket = new Socket(serverIP, serverPort);

        } catch (ConnectException e) {
            socket = initSocket(serverIP,serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
