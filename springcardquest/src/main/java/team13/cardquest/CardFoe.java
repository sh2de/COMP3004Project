package team13.cardquest;

public class CardFoe extends Card{
    private int power;
    private int boost;

    public CardFoe(String name, String type, int value, String url, Game game, int power, int boost){
        super(name, type, value, url, game);
        this.power = power;
        this.boost = boost;

        play = new PlayableFoe(this);
    }

    public int getPower(){return power;}
    public int getBoost(){return boost;}
}
