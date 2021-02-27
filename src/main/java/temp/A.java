package temp;

public class A implements O {

    @Override
    public String myGo() {
        return  "myGo: A";
    }

    @Override
    public String myGo(String m) {
        return  "myGo: A "+ m;
    }
}
