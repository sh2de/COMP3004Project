import java.util.ArrayList;

public class GameHandler {
    Deck storydeck = new Deck();
    int numPlayers = 0;
    int currentTurn = 1;
    ArrayList<Player> players = new ArrayList<>();


    public void addPlayer(Player p){
        players.add(p);
        numPlayers += 1;
    }

    public GameHandler(){

    }
}
