package team13.cardquest;

public class CardEvent extends Card{
    public CardEvent(String name, String type, int value, String url){
        super(name, type, value, url);

        //play = new PlayableEvent(this);
        setPlay(new PlayableEvent(this));
    }
}
