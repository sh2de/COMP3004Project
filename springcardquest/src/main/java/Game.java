import java.util.ArrayList;

//The game object represents the internal state of a game session
//It is responsible for keeping track of all the game elements, but does not automatically advance the game itself
public class Game {
    Deck storydeck = new Deck();
    Deck adventuredeck = new Deck();
    int numPlayers = 0;
    int currentTurn = 1;
    ArrayList<Player> players = new ArrayList<>();


    public void addPlayer(Player p){
        players.add(p);
        numPlayers += 1;
    }

    public void nextTurn(){
        currentTurn += 1;
        if (currentTurn > numPlayers){currentTurn = 1;}
    }

    public Game(){

    }
}
