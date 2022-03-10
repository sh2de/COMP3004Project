import java.util.ArrayList;

//The game object represents the internal state of a game session
//It is responsible for keeping track of all the game elements, but does not automatically advance the game itself
public class Game {
    Deck storydeck = new Deck();
    Deck adventuredeck = new Deck();
    int numPlayers = 0;
    int currentTurn = 1;
    
    String state = "initialize";
    /*the state variable is what will be used to check what moves are currently legal or not.
    depending on the state, the game may end up waiting to receive input from all players. (possibly have a timer to prevent getting stuck?)
    an index of the states will be described here below:
    "initialize"    wait for players to arrive. upon confirmation that all players have joined, start the game
    "turn start"    draw a card from the story deck
    "quest sponsor"   decide a sponsor for the quest. if the person who drew the card refuses to sponsor or cannot with their current cards, 
                    pass the choice to the next player. if all players refuse, discard the quest card.
    "quest setup"   the sponsor now decides which cards to play for the quest
                    NOTE FOR BEN: THEY SHOULD SET UP EVERYTHING ON THEIR END THEN SEND THE COMPLETE INPUT TO BE VALIDATED, IF ITS REJECTED THEY MUST RESELECT THE CARDS/ORDER
    "quest foe"     players must select the cards they wish to use before the foe is revealed and result is calculated    
    "quest test"    tbd
    
    */

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
