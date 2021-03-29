package execution_JSON;

import flexjson.JSONDeserializer;
import org.slf4j.Logger;

import java.io.PrintWriter;
import java.util.ArrayList;

public class MainExecList {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MainExecList.class);
    public int n ;
    public String command ;
    public ArrayList<Exec> arr ;

    public void deserializer(String json, PrintWriter printWriter){
        this.arr = new ArrayList<Exec>();
        JSONDeserializer<MainExecList> der = new JSONDeserializer<>();
        MainExecList result =  der.deserialize(json);
        log.info( "JSON: "+ json);
        log.info( "toString: "+ result.toString());

        result.arr.get(0).doProcess();
        result.arr.get(0).doProcess(result.n);
        result.arr.get(0).doProcess(result.command);
        result.arr.get(0).doProcess(printWriter);

    }

    public String toString() {
        return "n = "+n+" arg = "+command+" arr "+arr;
    }

}
