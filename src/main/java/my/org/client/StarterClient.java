package my.org.client;

import java.net.SocketException;



public class StarterClient {
    public static void main(String ... args) throws SocketException {
        String name="SP1",serverIP="192.168.56.101";
        int serverPort=8181;
        ClientController clientController = new ClientController();
        clientController.startSocket(name, serverIP, serverPort);
    }
}
