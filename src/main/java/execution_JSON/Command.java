package execution_JSON;

import my.org.client.ProcessModel;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

public class Command implements Exec{

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Command.class);
    @Override
    public void doProcess() {

    }

    @Override
    public void doProcess(int i) {

    }

    @Override
    public void doProcess(String command /* команда для исполнения */ ) {

            try {
                Runtime.getRuntime().exec(command);
                log.info("команда для исполнения: "+ command);
            }catch (NullPointerException e){
                log.info("Передано нулеве значение в doProcess(String command) ");
            }
            catch (IOException e) {
                log.info("ERROR: " , e);
            }

    }

    @Override
    public void doProcess(PrintWriter out) {

    }
}
