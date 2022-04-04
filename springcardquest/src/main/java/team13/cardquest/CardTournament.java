package team13.cardquest;

public class CardTournament extends Card{
    private int shields;

    public CardTournament(String name, String type, int value, String url, Game game, int shields){
        super(name, type, value, url, game);
        this.shields = shields;

        play = new PlayableTournament(this);
    }

    public int getShields(){return shields;}
}
