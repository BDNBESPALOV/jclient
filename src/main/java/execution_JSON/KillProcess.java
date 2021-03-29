package execution_JSON;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class KillProcess implements Exec{
    /* Logger */
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(KillProcess.class);

    @Override
    public void doProcess(int pid) {
        log.info("pid: "+pid);
        try {
            Runtime.getRuntime().exec("kill -9 "+pid);
        } catch (IOException e) {
            log.info("ERROR: "+e);
        }
    }

    @Override
    public void doProcess(String str) {

    }

    @Override
    public void doProcess(PrintWriter out) {

    }

    @Override
    public void doProcess() {

    }
}
