package my.org.client;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


@Controller
public class ClientController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientController.class);

    @Value("${connect.serverPort}")
    private int serverPort;

    @Value("${connect.serverIP}")
    private String serverIP;

    private String tempMessage;

    private String command;
    private Socket socket;
    PrintWriter out;
    BufferedReader in;

    public void startSocket() throws SocketException {


//        );
        try {
             socket = new Socket(serverIP, serverPort);
             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);

            out.println("clientSP1");
            System.out.println("clientSP1: "+socket);
            System.out.println("getInetAddress() "+socket.getInetAddress()+"\n"+
                "getKeepAlive "+socket.getKeepAlive()+"\n"+
                    "getChannel "+socket.getChannel()+"\n"+
                    "getOOBInline "+socket.getOOBInline()+"\n"
            );


//            while (true){
//                tempMessage=in.readLine();
//                if(command!=null){
//                    System.out.println("commandM:  "+command);
//                    out.println(command);
//                    command=null;
//                }
////                if(tempMessage.equals("Hi")){
////                    log.debug("Successful server connection serverIP: "+serverIP +" serverPort: "+serverPort);
////                    System.out.println("Successful server connection serverIP: "+serverIP +" serverPort: "+serverPort);
////                }
////
////                if(tempMessage.equals("END") || tempMessage.equals("end")){
////                    log.debug("Connection serverIP: "+serverIP +" serverPort: "+serverPort+" closed...");
////                    System.out.println("Connection serverIP: "+serverIP +" serverPort: "+serverPort+" closed...");
////                    break;
////                }else if (tempMessage.contains("Command:"))   {
////                    System.out.println("contains(Command:) tempMessage: "+tempMessage);
////                    tempMessage = tempMessage.substring(8,tempMessage.length());
////                    System.out.println("tempMessage.substring(0,7): "+tempMessage);
////
////                    Runtime rt = Runtime.getRuntime();
////                    Process proc = rt.exec(tempMessage);
////                }
//            }
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public void stopSocket() throws IOException {
        out.println("Disconnect clientSP1");
        socket.close();
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        JClient jClient = new JClient();
        model.addAttribute("jClient", jClient);
        return "index";
    }
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.POST)
    public String getCommand(Model model, //
                            @ModelAttribute("jClient") JClient jClient
    )  {
        command = jClient.getCommand();
        System.out.println("to server: "+command);
        out.println(command);
        return "index";
    }


    @RequestMapping(value = {"/stopRSocket" }, method = RequestMethod.POST)
    public  String stopRSocket(){
        try {
            stopSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("stopSocket");
        return "redirect:/";
    }
    @PostMapping("startRSocket")
    public  String startRSocket(){
        try {
            startSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        System.out.println("startSocket");
        return "redirect:/";
    }
    @PostMapping("test")
    public  String test(){
        System.out.println("test");
        return "redirect:/";
    }



}
