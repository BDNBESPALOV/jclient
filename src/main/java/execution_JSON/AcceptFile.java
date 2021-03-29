package execution_JSON;

import my.org.client.UploadFile;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class AcceptFile implements Exec{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AcceptFile.class);

    @Override
    public void doProcess() {
        Properties property = new Properties();
        int serverPort = Integer.parseInt(property.getProperty("server.port"));
        String serverIP = property.getProperty("server.ip");
        try {
            new UploadFile(initSocket(serverIP,serverPort));
        } catch (NoSuchAlgorithmException e) {
            log.info("ERROR: " , e);
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
            log.info("ERROR: " , e);
        } catch (IOException e) {
            log.info("ERROR: " , e);
        }
        return socket;
    }
}
