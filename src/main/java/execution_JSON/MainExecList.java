package execution_JSON;

import flexjson.JSONDeserializer;
import org.slf4j.Logger;

import java.io.PrintWriter;
import java.util.ArrayList;

public class MainExecList {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MainExecList.class);
    public int pid ;
    public String command ;
    public ArrayList<Exec> arr ;

    public void deserializer(String json, PrintWriter printWriter){

        this.arr = new ArrayList<Exec>();
        JSONDeserializer<MainExecList> der = new JSONDeserializer<>();
        MainExecList result =  der.deserialize(json);
        log.info( "JSON: "+ json);
        log.info( "toString: "+ result.toString());
        log.info( result.arr.get(0).toString());

        result.arr.get(0).doProcess(pid,command,printWriter);

    }

    public String toString() {
        return "n = "+pid+" arg = "+command+" arr "+arr;
    }

}
