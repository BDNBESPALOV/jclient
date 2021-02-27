package execution_JSON;

import flexjson.JSONDeserializer;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainExecList {

    public int n ;
    public String command ;
    public ArrayList<Exec> arr ;

    public void deserializer(String json, PrintWriter printWriter){
        this.arr = new ArrayList<Exec>();
        JSONDeserializer<MainExecList> der = new JSONDeserializer<>();
        MainExecList result =  der.deserialize(json);
        System.out.println( "JSON: "+ json);
        System.out.println( "toString: "+ result.toString());

        result.arr.get(0).doProcess();
        result.arr.get(0).doProcess(result.n);
        result.arr.get(0).doProcess(result.command);
        result.arr.get(0).doProcess(printWriter);

    }

    public String toString() {
        return "n = "+n+" arg = "+command+" arr "+arr;
    }

}
