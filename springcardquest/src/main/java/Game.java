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
        addPlayer(new Player());
        addPlayer(new Player());
        addPlayer(new Player());
        addPlayer(new Player());
        
        for (Player player : players) {
            for(int i = 0; i < 12; i++){
                player.addCardToHand(adventuredeck.draw());
            }
        }

    }

    public void print(){
        System.out.println("It is player "+currentTurn+"'s turn.");
        int pnum = 1;
        for (Player player : players) {
            System.out.println("Showing player "+(pnum++)+"'s information:");
            player.print();
        }
    }

}
