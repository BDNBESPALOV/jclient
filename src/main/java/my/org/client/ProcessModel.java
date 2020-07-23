package my.org.client;

import org.slf4j.Logger;

import java.io.*;

public class ProcessModel {
    /* Logger */
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProcessModel.class);
    private final String pathUpdate = "/u02/1.40.0.285/1.40.0.285/SQL/DBUpdate.sh";

    public String inController;

    public void killProcess(String pid){
        log.info("pid: "+pid);
        try {
            Runtime.getRuntime().exec("kill -9 "+pid);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void process(PrintWriter out)  {

        try {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "ps -e --format='pid cmd'|grep java");

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            int index=0;
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.charAt(0) == ' '){
                    line = line.substring(1);
                }

                index = line.indexOf(" ");
                if (index>0){
                    if (line.contains(" grep java")){
                        continue;
                    }
                    out.println("Key:" + line.substring(0,index) + "Value:" + line.substring(index));

                }

            }

            process.waitFor();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeSQLScript( PrintWriter outController) throws IOException {
        String inputLine;
        String line ;

        /* Linux */
        Process p = Runtime.getRuntime().exec(pathUpdate);

        /* Windows test */
        // Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c","type","order.log"});

        InputStream in = p.getInputStream();
        OutputStream outputStream = p.getOutputStream();

        BufferedReader inb = new BufferedReader(new InputStreamReader(in,"UTF-8"));


            /*в цикле считываем сообщения от SPAdmin */
            boolean varTemp = true;
            while ((inputLine = inb.readLine()) != null) {

                /*отправлять все ответы от SPAdmin */
                outController.println(inputLine);

                if (inputLine.contains("Found")) {
                    log.info("Начало деалога");
                    while(varTemp) {
                        log.info("inController "+inController);
                        if (inController.equals("Y")) {
                            log.info("Пользователь ответил Да");
                            line = "Y" + "\n";
                            outputStream.write(line.getBytes());
                            outputStream.flush();
                            varTemp = false;
                        } else if (inController.equals("N")) {
                            line = "N" + "\n";
                            log.info("Пользователь ответил Нет");
                            outputStream.write(line.getBytes());
                            outputStream.flush();
                            varTemp = false;
                        }
                    }

                }

                log.info(inputLine);
            }



    }

}
