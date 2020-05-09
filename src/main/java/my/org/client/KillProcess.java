package my.org.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class KillProcess {

    public void kill(String pid){
        System.out.println("pid: "+pid);
        try {
            Runtime.getRuntime().exec("kill -9 "+pid);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
