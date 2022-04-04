package team13.cardquest;

public class CardWeapon extends Card{
    private int power;

    public CardWeapon(String name, String type, int value, String url, Game game, int power){
        super(name, type, value, url, game);
        this.power = power;

        play = new PlayableWeapon(this);
    }

    public int getPower(){return power;}
}
