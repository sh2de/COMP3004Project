package team13.cardquest;

public class CardEvent extends Card{
    public CardEvent(String name, String type, int value, String url, Game game){
        super(name, type, value, url, game);

        play = new PlayableEvent(this);
    }
}
