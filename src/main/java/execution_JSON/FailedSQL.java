package execution_JSON;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FailedSQL implements Exec {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FailedSQL.class);


    @Override
    public void doProcess(int pid,String command,PrintWriter out) {

            try {
                Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c","type","order.log"});
                OutputStream outputStream = p.getOutputStream();
                outputStream.write(command.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                log.info("ERROR: "+e);
            }

    }


}
