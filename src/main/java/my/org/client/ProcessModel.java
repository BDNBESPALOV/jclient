package my.org.client;


import org.slf4j.Logger;

import java.io.*;
import java.util.Date;

public class ProcessModel {
    /* Logger */
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProcessModel.class);
    //private final String pathUpdate = "/u02/1.40.0.285/1.40.0.285/SQL/DBUpdate.sh";


    /* Linux */
    // Process p = Runtime.getRuntime().exec(pathUpdate);
    //Process p = Runtime.getRuntime().exec("./DBUpdate.sh",null,new File("/u02/1.40.0.285/1.40.0.285/SQL"));
    /* Windows test */
    Process p;

    {
        try {
            p = Runtime.getRuntime().exec(new String[]{"cmd", "/c","type","order.log"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    InputStream in = p.getInputStream();
    OutputStream outputStream = p.getOutputStream();




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

            int index = 0;
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


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeSQLScript( PrintWriter outController) throws IOException {
        String inputLine;

        BufferedReader inb = new BufferedReader(new InputStreamReader(in,"UTF-8"));

            /*в цикле считываем сообщения от SPAdmin */
            boolean varTemp = true;
            while ((inputLine = inb.readLine()) != null) {
                /*отправлять все ответы от SPAdmin */
                outController.println(inputLine);



                log.info(inputLine);
            }
            log.info(inputLine);
            in.close();
            inb.close();
           // outController.close();

    }


    public void setFAILEDCommand(String command){
        try {
            outputStream.write(command.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
