package team13.cardquest;

public class CardArmour extends Card{
    private int power;

    public CardArmour(String name, String type, int value, String url, Game game, int power){
        super(name, type, value, url, game);
        this.power = power;

        play = new PlayableArmour(this);
    }

    public int getPower(){return power;}
}
