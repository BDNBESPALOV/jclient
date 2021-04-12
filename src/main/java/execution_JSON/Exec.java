package execution_JSON;

import java.io.PrintWriter;

public interface Exec {
    void doProcess(int pid,String str,PrintWriter out);
}
