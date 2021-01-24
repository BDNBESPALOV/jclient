package my.org.client.execution;

import java.io.IOException;
import java.io.PrintWriter;

public class Command implements Exec{
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doProcess(PrintWriter out) {

    }
}
