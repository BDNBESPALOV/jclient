package my.org.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String [] args){
//        try {
//            Runtime.getRuntime().exec("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "ps -e --format='pid cmd'|grep java > ps.log");
            System.out.println(".......");

            Process process = processBuilder.start();
//
//            StringBuilder output = new StringBuilder();
//
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line + "\n");
//            }

            int exitVal = process.waitFor();
//            if (exitVal == 0) {
//                System.out.println("Success!");
//                System.out.println(output);
//                System.exit(0);
//            } else {
//                //abnormal...
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
