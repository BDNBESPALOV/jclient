package my.org.client;


import execution_JSON.MainExecList;
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

    private final ProcessModel processModel = new ProcessModel();


    public void openSocket( String name, String serverIP,
            int serverPort)  {
            String inputLine;

//        new Thread(
//                () -> {
                    try(
                            Socket socket = initSocket(serverIP, serverPort);
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(new BufferedWriter(
                                    new OutputStreamWriter(socket.getOutputStream())), true)
                            ) {

                        /* отправляем название экземпляра клиента,
                        для регистрации его в таблицы клиентов */
                        out.println("client"+name);

                        /* в цикле считываем сообщения от сервера */
                        while ((inputLine = in.readLine()) != null) {
                            log.info("Server messages: " + inputLine);

                            new MainExecList().deserializer(inputLine,out);

                            /*проверяем, что от сервера пришла команда для исполнения */
                            /*===================================================================*/
//                        if (inputLine.contains("Command:")) {
//                            inputLine = inputLine.substring(8);
//                            Runtime.getRuntime().exec(inputLine);
//                        }
                            /*проверяем, что от сервера пришла команда для monitoringProcess */
                            /*===================================================================*/
//                        }else if (inputLine.contains("monitoringProcess:"))   {
//                            processModel.process(out);
//                            }
                            /*===================================================================*/
                            /* проверяем, что от сервера пришла команда на удаление процесса */
//                        else if (inputLine.contains("Killed: ")){
//                            processModel.killProcess(inputLine.substring(8));
//                        }

                            /* проверяем, что от сервера пришла команда на загрузку патча */
/*===================================================================*/
//                        else if (inputLine.contains("accept file")){
//
//                            log.info("accept file : " + inputLine);
//                            try {
//                                new UploadFile(initSocket(serverIP,serverPort));
//                            } catch (NoSuchAlgorithmException e) {
//                                log.info("ERROR: "+e);
//                            }
//
//                        }
                        /* проверяем, что от сервера пришла команда на обновление SP */
//                        else if (inputLine.contains("UpdateSP: ")){
//                            log.info("от сервера пришла команда на обновление SP: " + inputLine);
//                            /* проверка, что запрос содержит только команду на обновление  */
//                            if (inputLine.length() <= 10){
//                               // new Thread( ()->{
//                                    try {
//                                        processModel.executeSQLScript(out);
//                                    } catch (IOException e) {
//                                        log.info("ERROR: "+e);
//                                    }
//                              //  }).start();
//                                /* проверка ответа на запрос выполнения SQL */
//                            } else {
//                                InController.setInController(inputLine.substring(10));
//                                log.info("проверка ответа на запрос выполнения SQL: " + InController.getInController() );
//
//                            }
//                        }
                        /* команды когда скрипт завершается с ошибкой */
                        if(inputLine.contains("FAILED_Break")){
                            processModel.setFAILEDCommand("B");
                        }

                        if(inputLine.contains("FAILED_Continue")){
                            processModel.setFAILEDCommand("C");
                        }

                        if(inputLine.contains("FAILED_Rollback")){
                            processModel.setFAILEDCommand("R");
                        }

                        if(inputLine.contains("FAILED_RRollback")){
                            processModel.setFAILEDCommand("RR");
                        }

                        /*////*/

                        }
                        if (inputLine.contains("UpdateXML: ")){
                            log.info("от сервера пришла команда на выполнение XML: " );
                            out.println("команда на выполнение XML принята !");
                        }


                    }  catch (IOException e) {
                        log.info("ERROR: "+e);
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
            log.info("ERROR: "+e);
        } catch (IOException e) {
            log.info("ERROR: "+e);
        }
        return socket;
    }




}
