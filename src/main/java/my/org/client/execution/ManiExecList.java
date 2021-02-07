package my.org.client.execution;

import java.util.ArrayList;

public class ManiExecList {
    public ArrayList<Exec> execution;

    public void addExec(Exec exec){
        ArrayList<Exec> arrayList = new ArrayList<>();
        arrayList.add(exec);
        this.execution = arrayList;

    }}
