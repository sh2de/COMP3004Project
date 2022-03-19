package team13.cardquest;

public abstract class Card {
    public String url = "./images/testcard.png";
    public long id = -1;

    public boolean initQuest(Quest q){
        return false;
    }

    public String toString(){
        return "GENERIC CARD :( ";
    }

    public String getType(){
        return "UNDEFINED";
    }

    public int getPower(){
        return 0;
    }
	
	public abstract void play();
}
