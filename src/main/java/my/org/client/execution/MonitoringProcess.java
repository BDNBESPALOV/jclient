package my.org.client.execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MonitoringProcess implements Exec{
    @Override
    public void doProcess(int i) {

    }

    @Override
    public void doProcess(String str) {

    }

    @Override
    public void doProcess(PrintWriter out) {

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

    @Override
    public void doProcess() {

    }
}
