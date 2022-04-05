package team13.cardquest;

public class CardQuest extends Card{
    private int stages;
    private String namedFoe;

    public CardQuest(String name, String type, int value, String url, Game game, int stages, String namedFoe){
        super(name, type, value, url);
        this.stages = stages;
        this.namedFoe = namedFoe;

        //play = new PlayableQuest(this);
        setPlay(new PlayableQuest(this));
    }

    public int getStages(){return stages;}
    public String getNamedFoe(){return namedFoe;}
}
