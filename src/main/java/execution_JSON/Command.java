package execution_JSON;


import org.slf4j.Logger;

import java.io.*;

public class Command implements Exec{

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Command.class);

    @Override
    public void doProcess(int pid,String command,PrintWriter out) {

            try {
                Process process =   Runtime.getRuntime().exec(command);

                log.info("команда для исполнения: "+ command);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    log.info("ответ exec : "+ line);
                }
            } catch ( IllegalThreadStateException e){
                log.info("ERROR: " , e);
            }
            catch (NullPointerException e){
                log.info("Передано нулеве значение в doProcess(String command) ");
            }
            catch (IOException e) {
                log.info("ERROR: " , e);
            }

    }


}
