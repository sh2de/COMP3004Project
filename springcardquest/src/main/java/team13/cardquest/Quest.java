package team13.cardquest;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Quest {
    String name = "DUMMYQUEST";
    int stages = 3;
    String bossname = "DUMMYFOE";
    long bossid = -1;
    ArrayList<ArrayList<Card>> stageHands = new ArrayList<>();

    public Quest(){}

    public void setParams(String _name, int _stages, String _bossname, long _bossid){
        name = _name;
        stages = _stages;
        bossname = _bossname;
        bossid = _bossid;
    }

    public int getStages(){
        return stages;
    }

    
}
