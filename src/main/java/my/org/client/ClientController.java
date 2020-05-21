package my.org.client;

import org.slf4j.Logger;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class ClientController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientController.class);

    public void startSocket( String name, String serverIP,
            int serverPort)  {
            String inputLine;
            ProcessModel processModel = new ProcessModel();
//        new Thread(
//                () -> {
                    try(
                            Socket socket = initSocket(serverIP, serverPort);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream())), true)
                            ) {

                        /*отправляем название экземпляра клиента, для регистрации его в таблицы клиентов */
                        out.println("client"+name);

                        /*в цикле считываем сообщения от сервера */
                        while ((inputLine = in.readLine()) != null) {
                            System.out.println("inputLine: " + inputLine);

                        /*проверяем, что от сервера пришла команда для исполнения */
                        if (inputLine.contains("Command:"))   {
                            inputLine = inputLine.substring(8,inputLine.length());
                            Runtime.getRuntime().exec(inputLine);

                        /*проверяем, что от сервера пришла команда для monitoringProcess */
                        }else if (inputLine.contains("monitoringProcess:"))   {
                            processModel.process(out);
                            }
                        /* проверяем, что от сервера пришла команда на удаление процесса */
                        else if (inputLine.contains("Killed: ")){
                            processModel.killProcess(inputLine.substring(8));
                        }

                        /* проверяем, что от сервера пришла команда на загрузку патча */
                        else if (inputLine.contains("accept file")){

                            System.out.println("accept file : " + inputLine);
                            new UploadFile(initSocket(serverIP,serverPort));

                        }

                        /* проверяем, что от сервера пришла команда на обновление SP */
                        else if (inputLine.contains("UpdateSP: ")){

                            processModel.updateSP(inputLine.substring(10),out);


                        }


                        }


                    }  catch (IOException e) {
                        e.printStackTrace();
                    }
//                }
//        ).start();
    }

    private Socket initSocket(String serverIP,int serverPort){
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
