package team13.cardquest;

public class CardArmour extends Card{
    private int power;

    public CardArmour(String name, String type, int value, String url, int power){
        super(name, type, value, url);
        this.power = power;

        //play = new PlayableArmour(this);
        setPlay(new PlayableArmour(this));
    }

    public int getPower(){return power;}
}
