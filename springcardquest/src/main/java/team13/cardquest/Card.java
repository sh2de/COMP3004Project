package team13.cardquest;

public abstract class Card {
    protected long id = -1;
    protected String name = "UNNAMED";
    protected String type = "UNDEFINED";
    protected int bidWorth = 1;
    protected String url = "./images/testcard.png";
    protected int frequency=0;

    public boolean initQuest(Quest q){
        return false;
    }

    public String toString(){
        return "GENERIC CARD :( ";
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getBidWorth(){
        return bidWorth;
    }

    public int getFrequency(){
        return frequency;
    }

    public String getUrl(){
        return url;
    }


    //doesn't make sense for Card to have, should be for specific type of
    public int getPower(){
        return 0;
    }
	
	public abstract void play();
}
