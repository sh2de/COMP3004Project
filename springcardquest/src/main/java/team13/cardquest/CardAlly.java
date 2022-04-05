package team13.cardquest;

public class CardAlly extends Card{
    private int power;

    public CardAlly(String name, String type, int value, String url, int power){
        super(name, type, value, url);
        this.power = power;

        //play = new PlayableAlly(this);
        setPlay(new PlayableAlly(this));
    }

    public int getPower(){return power;}
}
