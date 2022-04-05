package team13.cardquest;

public class CardWeapon extends Card{
    private int power;

    public CardWeapon(String name, String type, int value, String url, int power){
        super(name, type, value, url);
        this.power = power;

        //play = new PlayableWeapon(this);
        setPlay(new PlayableWeapon(this));
    }

    public int getPower(){return power;}
}
