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
}
