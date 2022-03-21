package team13.cardquest;

public class QuestCard extends StoryCard{
    private int stages;
    private String foeName;
    private int foeId;

    public QuestCard(String name, int stages, String foeName, int bidWorth, String url){
        id = -10;
        type = "Quest";
        this.name = name;
        this.stages = stages;
        this.foeName = foeName;
        this.foeId = -3;
        this.bidWorth = bidWorth;
        this.url = url;
    }

    public boolean initQuest(Quest q){
        q.setParams(name, stages, foeName, foeId);
        return true;
    }

    public String toString(){
        return name + ", a " + stages + " stage quest featuring " + foeName;
    }

    public String getType(){
        return "QUEST";
    }

    @Override
    public void play() {

    }
}
