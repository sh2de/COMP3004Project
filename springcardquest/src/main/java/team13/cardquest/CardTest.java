package team13.cardquest;

public class CardTest extends Card{
    private int minBid;

    public CardTest(String name, String type, int value, String url, Game game, int minBid){
        super(name, type, value, url, game);
        this.minBid = minBid;

        play = new PlayableTest(this);
    }

    public int getMinBid(){return minBid;}
}
