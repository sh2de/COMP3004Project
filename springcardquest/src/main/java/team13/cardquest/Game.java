package team13.cardquest;

import java.util.ArrayList;

//The game object represents the internal state of a game session
//It is responsible for keeping track of all the game elements, but does not automatically advance the game itself
public class Game {
    DeckFactory df = new DeckFactory(this);
    Deck storydeck = df.CreateDeck(true);
    Deck adventuredeck = df.CreateDeck(false);
    int numPlayers = 0;
    int currentTurn = 1;
    int sponsor = -1; //variable used for iterating through available sponsors
    Player currentSponsor = null;
    ArrayList<Player> players = new ArrayList<>();

    Card currentStory = null;

    BlobQuest activeQuest = null;


    String state = "initialize";
    /*the state variable is what will be used to check what moves are currently legal or not.
    depending on the state, the game may end up waiting to receive input from all players. (possibly have a timer to prevent getting stuck?)
    an index of the states will be described here below:
    "initialize"    wait for players to arrive. upon confirmation that all players have joined, start the game
    "turn start"    draw a card from the story deck upon receiving "draw" command from the current player
    "quest sponsor"   decide a sponsor for the quest. if the person who drew the card refuses to sponsor or cannot with their current cards, 
                    pass the choice to the next player. if all players refuse, discard the quest card.
    "quest setup"   the sponsor now decides which cards to play for the quest
    "quest foe"     players must select the cards they wish to use before the foe is revealed and result is calculated    
    "quest test"    tbd
    */

    //helper function to check if no players are waiting
    public boolean allPlayersReady(){
        boolean flag = true;
        for (Player player : players) {
            if (player.getWaiting()){flag = false;}
        }
        return flag;
    }
    
    //helper function to make all players ready
    public void forceAllReady(){
        for (Player player : players) {
            player.setWaiting(false);
        }
    }

    //helper function to make all players not ready
    public void forceAllUnready(){
        for (Player player : players) {
            player.setWaiting(true);
        }
    }

    public Player getPlayer(String name){
        for (Player player : players) {
            if(player.getName().equals(name)){
                return player;
            }
        }
        return new Player("");
    }

    public Game(){
        
    }

    //GAME SETUP FUNCTIONS---------------------------------------------------------------------------------------------------------------------
    //api call to add new player to the game
    public String joinGame(String name){
        if (numPlayers > 3){
            return "";
        }

        for (Player player : players) { //edit duplicate names
            if (player.getName().equals(name)){name += (numPlayers+1);}
        }

        //missing functionality: make sure there are no duplicate player names
        addPlayer(new Player(name));
        System.out.println("player "+name+" has joined the game"); //debug message
        return name;
    }

    public void addPlayer(Player p){
        players.add(p);
        numPlayers += 1;
    }

    //api call to set player as ready to start the game
    public boolean startGame(String name){
        //set player as ready to start, if all players are ready give them all the “ALL_PLAYERS_READY” signal
        getPlayer(name).setWaiting(false);
        if (allPlayersReady()){
            for (Player player : players) {
                player.addEventSignal("ALL_PLAYERS_READY");
                for(int i = 0; i < 12; i++){
                    player.addCardToHand(adventuredeck.draw());
                }
            }
            state = "turn_start"; //set the internal state to begin the game
            turnStart();
        }
        return true;
    }


    //TURN PROCESSING FUNCTIONS----------------------------------------------------------------------

    //helper function to pass to the next turn no matter the number of players
    public void nextTurn(){
        currentTurn += 1;
        if (currentTurn > numPlayers){currentTurn = 1;}
    }

    //function to be called at the start of a turn. draws a card for the current player and sends the proper signals
    public void turnStart(){
        currentStory = storydeck.draw();

        if (currentStory.GetType().equals("QUEST")){
            //quest card drawn
            for (Player player : players) {
                player.addEventSignal("DRAW_STORY");
            }
            state = "quest sponsor";
            sponsor = 0;
            currentStory.Play();
            forceAllUnready();
            getSponsor();

        } else if (currentStory.GetType().equals("EVENT")){
            //event card drawn

        } else if (currentStory.GetType().equals("TOURNAMENT")){
            //tournament card drawn

        } else {
            //invalid card
            currentStory = null;
            turnStart();
        }
        


        //activeQuest = new Quest();
        //c.initQuest(activeQuest);
        //state = "quest sponsor";
        //sponsor = 0;
        //forceAllUnready();

    }

    //QUEST PROCESSING FUNCTIONS----------------------------------------------------------------------
    
    /*quest behavior as follows
    quest card is drawn
    for each player, check if they can sponsor it. it yes, send signal to ask if they wish to do so. if not, pass option to next player who can sponsor it
    if no players can sponsor it, end turn
    if a player decides to sponsor it, request the cards they wish to sponsor it with
    once received, proceed with quest. send signal to each player who is still alive to pick cards for battle
    receive cards for each player. once all have been received, show the results, repeat until all phases are done or all players fail
    */

    public void ReceiveQuest(BlobQuest q){
        activeQuest = q;
    }
    
    public void getSponsor(){ //function to be called to iterate through possible sponsors for a quest
        if (allPlayersReady()){ //if all players rejected the sponsor, discard the quest and begin a new turn
            state = "turn_start";
            activeQuest = null;
            nextTurn();
            return;
        }

        Player p = players.get((currentTurn - 1 + sponsor)%numPlayers);
        sponsor++;

        if (p.canSponsor(activeQuest)){
            p.addEventSignal("REQUEST_SPONSORSHIP"); //signal to ask if the player would like to sponsor the following quest
        } else {
            p.setWaiting(false);
            getSponsor();
        }
        return;
    }

    public void sponsorshipDeclined(){//function that runs when a player rejects a quest
        getSponsor();
    }

    public void sponsorshipAccepted(){//function that runs when a player accepts a quest to sponsor to signal other players
        forceAllUnready(); //we need a response from all players
        for (Player player : players) {
            if ((player).equals(currentSponsor)){
                player.addEventSignal("CREATE_QUEST"); //signal to select cards for the quest
            } else {
                player.addEventSignal("WAIT_FOR_QUEST_CREATION"); //signal to know that a quest is about to begin and request participation
            }
        }
    }

    //this function is what each quest will call so that the game can put itself in the right state for the quest
    //public void ReceiveQuest(BlobQuest q){System.out.println(q.name + " " + q.stages + " " + q.namedFoe);}
    public void ReceiveAlly(BlobAlly a){System.out.println(a.name + " " + a.power + " " + a.value);}
    public void ReceiveFoe(BlobFoe f){System.out.println(f.name + " " + f.power + " " + f.boost);}
    public void ReceiveWeapon(BlobWeapon w){System.out.println(w.name + " " + w.power);}

    public boolean defeatedFoe(Player p, ArrayList<Card> playerCards, ArrayList<Card> questCards){ //check if the player successfully defeated a foe or not
        if (p.getPower() + getPower(playerCards) >= getPower(questCards)){return true;}
        return false;
    }

    public int getPower(ArrayList<Card> hand){//get power sum of the current combination of cards
        int p = 0;
        for (Card card : hand) {
            //p += card.GetPower();
        }
        return p;
    }

    //EVENT PROCESSING FUNCTIONS--------------------------------------------------------------------

    //TOURNAMENT PROCESSING FUNCTIONS----------------------------------------------------------------

    //GENERAL USE FUNCTIONS---------------------------------------------------------------------------


    //api call to get a player's list of update signals
    public String getUpdates(String name){
        return getPlayer(name).sendEventQueue();
    }

    //api call to get a current player

    public Player getCurrentPlayer(){
        return players.get(currentTurn-1);
    }
    

    //print function for debugging purposes
    public void print(){
        System.out.println(state);
        System.out.println("It is player "+currentTurn+"'s turn.");
        int pnum = 1;
        for (Player player : players) {
            System.out.println("Showing player "+(pnum++)+"'s information:");
            player.print();
        }
    }
}