package my.org.client.temp;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.util.ArrayList;

public class Cat {

    public String name; // имя
    public ArrayList<O> arr ;

    public void addCat(O cat){
        ArrayList<O> arr = new ArrayList<>();
        arr.add(cat);
        this.arr = arr;
    }
   // public Address address;


    public static void main(String[] args){
        Cat murzik2 = new Cat();
        murzik2.name = "Мурзик";
        murzik2.addCat(new A());

        JSONSerializer ser = new JSONSerializer();
        String json = ser.deepSerialize(murzik2);
        System.out.println( json);

        JSONDeserializer<Cat> der = new JSONDeserializer<>();
        Cat sh2 = der.deserialize(json);
        System.out.println( sh2.arr.get(0).myGo());

    }
}
