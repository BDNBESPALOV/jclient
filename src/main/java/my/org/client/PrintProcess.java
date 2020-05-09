package my.org.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PrintProcess {

    //private final String FILE_NAME = "A:\\JAVAPROJEСTS\\test\\ps.log";  /*ps -e --format="pid cmd"|grep java > ps.log*/
    //private final String FILE_NAME = "./ps.log";

    public void process(PrintWriter out) throws IOException {

        try {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "ps -e --format='pid cmd'|grep java");

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            int index=0;
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


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
