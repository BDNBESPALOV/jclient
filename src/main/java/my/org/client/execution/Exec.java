package my.org.client.execution;

import java.io.PrintWriter;

public interface Exec {
    void doProcess();
    void doProcess(int i);
    void doProcess(String str);
    void doProcess(PrintWriter out);
}
