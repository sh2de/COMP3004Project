package team13.cardquest;

public class QuestCard extends StoryCard{
    String name = "DUMMYQUEST";
    int stages = 3;
    String bossname = "DUMMYFOE";
    long bossid = -1;

    public QuestCard(long _id, String _url, String _name, int _stages, String _bossname, long _bossid){
        id = _id;
        url = _url;
        name = _name;
        stages = _stages;
        bossname = _bossname;
        bossid = _bossid;
    }

    public boolean initQuest(Quest q){
        q.setParams(name, stages, bossname, bossid);
        return true;
    }

    @Override
    public String toString(){
        return name + ", a " + stages + " stage quest featuring " + bossname;
    }
}
