package my.org.client;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



@Controller
public class ClientController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientController.class);

    @Value("${connect.serverPort}")
    private int serverPort;

    @Value("${connect.serverIP}")
    private String serverIP;

    private String tempMessage;

    private String command;


    public void ClientController1(){
        try(Socket socket = new Socket(serverIP, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            ) {


            out.println("clientSP1");


            while (true){
                tempMessage=in.readLine();
                if(command!=null){
                    System.out.println("commandM:  "+command);
                    out.println(command);
                    command=null;
                }
//                if(tempMessage.equals("Hi")){
//                    log.debug("Successful server connection serverIP: "+serverIP +" serverPort: "+serverPort);
//                    System.out.println("Successful server connection serverIP: "+serverIP +" serverPort: "+serverPort);
//                }
//
//                if(tempMessage.equals("END") || tempMessage.equals("end")){
//                    log.debug("Connection serverIP: "+serverIP +" serverPort: "+serverPort+" closed...");
//                    System.out.println("Connection serverIP: "+serverIP +" serverPort: "+serverPort+" closed...");
//                    break;
//                }else if (tempMessage.contains("Command:"))   {
//                    System.out.println("contains(Command:) tempMessage: "+tempMessage);
//                    tempMessage = tempMessage.substring(8,tempMessage.length());
//                    System.out.println("tempMessage.substring(0,7): "+tempMessage);
//
//                    Runtime rt = Runtime.getRuntime();
//                    Process proc = rt.exec(tempMessage);
//                }
            }


        }catch (IOException e){
            e.getStackTrace();
        }

    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        JClient jClient = new JClient();
        model.addAttribute("jClient", jClient);
        return "index";
    }
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.POST)
    public String findInLog(Model model, //
                            @ModelAttribute("jClient") JClient jClient
    )  {
        command = jClient.getCommand();

        System.out.println("command:  "+command);
        return "index";
    }





}
