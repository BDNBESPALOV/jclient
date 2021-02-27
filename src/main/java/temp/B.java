package temp;

public class B implements O {
    @Override
    public String myGo() {
        return "myGo: B";
    }
    @Override
    public String myGo(String m) {
        return  "myGo: B "+ m;
    }
}
