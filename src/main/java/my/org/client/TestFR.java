package my.org.client;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class TestFR {

    private static final String FILE_NAME = "D:\\ps.log";  /*ps -e --format="pid cmd"|grep java > ps.log*/

    public HashMap<String,String> getPS() throws IOException {
        HashMap hashMap =  new HashMap<>();
        int index=0;

        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
        for(String line: lines){
            index = line.indexOf("/");
            if (index>0){

                hashMap.put(line.substring(0,index),line.substring(index));

                System.out.println("pid:");
                System.out.println(line.substring(0,index));
                System.out.println("cmd:");
                System.out.println(line.substring(index));
            }

            System.out.println("all:");
            System.out.println(line);
        }
        return hashMap;
    }


    public static void main(String ... args) {

    }
}
