package team13.cardquest;

public class CardAlly extends Card{
    private int power;

    public CardAlly(String name, String type, int value, String url, Game game, int power){
        super(name, type, value, url, game);
        this.power = power;

        play = new PlayableAlly(this);
    }

    public int getPower(){return power;}
}
