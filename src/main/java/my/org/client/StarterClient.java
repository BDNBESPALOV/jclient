package my.org.client;


import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class StarterClient {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StarterClient.class);
    public static void main(String ... args)  {
        FileInputStream fis;
        Properties property = new Properties();
        String name,serverIP;
        int serverPort;

        try {
            /*A:\temp*/
            fis = new FileInputStream("Client.properties");
            property.load(fis);
            name = property.getProperty("client.name");
            serverIP = property.getProperty("server.ip");
            serverPort = Integer.parseInt(property.getProperty("server.port"));

            log.info("client.name "+property.getProperty("client.name"));
            log.info("server.port "+property.getProperty("server.port"));
            log.info("server.ip "+property.getProperty("server.ip"));


            my.org.client.ClientController clientController = new my.org.client.ClientController();
            clientController.startSocket(name, serverIP, serverPort);
        }catch (IOException e){
            System.err.println("ERROR: Файл свойств отсуствует!");
        }

    }


}
