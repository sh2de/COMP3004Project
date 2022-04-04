package team13.cardquest;

public class CardAlly extends Card{

    public CardAlly(String name, String type, int value, String url, Game game, int num){
        super(name, type, value, url, game);
    }
    public int getPower(){return 0;}
}