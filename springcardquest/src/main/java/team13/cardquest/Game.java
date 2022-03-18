package team13.cardquest;

import java.util.ArrayList;

//The game object represents the internal state of a game session
//It is responsible for keeping track of all the game elements, but does not automatically advance the game itself
public class Game {
    Deck storydeck = new Deck(true);
    Deck adventuredeck = new Deck(false);
    int numPlayers = 0;
    int currentTurn = 1;
    ArrayList<Player> players = new ArrayList<>();

    //StoryCard currentStory = null;

    Quest activeQuest = null;


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

    //function that updates the game according to its state
    //returns true/false depending on if the command is valid
    public boolean update(String executor, String command){ //executor is the player name, command is the action
        System.out.println("game has received command "+command+" from player "+executor);
        boolean flag = false;
        switch(state){
            case "initialize":
                switch(command){
                    case "join": //join command is to join the game
                        if (numPlayers < 4){ //check to make sure that there arent more players than can play the game
                            //missing----------check that no 2 players share a name/id
                            addPlayer(new Player(executor));
                            flag = true;

                        }
                        break;
                    case "startgame":
                        System.out.println("attempting to start the game");
                        int waitCounter = 0;
                        for (Player
                                player : players) {
                            if (player.getName().equals(executor)){
                                System.out.println("player found!");
                                player.setWaiting(false);
                                flag = true;
                            }
                            if (!player.getWaiting()){waitCounter++;}
                        }
                        System.out.print(waitCounter);
                        if(waitCounter == numPlayers && numPlayers > 1){
                            state = "turn start";
                            for (Player player : players) {
                                for(int i = 0; i < 12; i++){
                                    player.addCardToHand(adventuredeck.draw());
                                }
                            }
                            System.out.println("Open The Game!!!");
                        }

                        break;
                }
                return flag;
            case "turn start":
                System.out.println(currentTurn);
                System.out.println(executor);
                System.out.println(players.get(currentTurn-1).getName());
                if (executor.equals(players.get(currentTurn-1).getName()) && command.equals("draw")){
                    flag = true;
                    //if the correct player draws, handle the turn here
                    Card c = storydeck.draw();
                    
                    System.out.println("player drew card "+c);
                    activeQuest = new Quest();
                    c.initQuest(activeQuest);
                    state = "quest sponsor";
                    
                    
                } else {
                    System.out.println("INCORRECT COMMAND/PLAYER");
                }
                return flag;
            case "quest sponsor":
                return flag;
            case "quest setup":
                return flag;
            case "quest foe":
                return flag;
            case "quest test":
                return flag;
            default:
                return flag;
        }

        //return false;

    }
    
    //function to be called at the start of a turn. draws a card for the current player and sends the proper signals
    public void turnStart(){
        Card c = storydeck.draw();
        activeQuest = new Quest();
        c.initQuest(activeQuest);
        state = "quest sponsor";

    }

    public void addPlayer(Player p){
        players.add(p);
        numPlayers += 1;
    }

    //helper function to check if no players are waiting
    public boolean allPlayersReady(){
        boolean flag = true;
        for (Player player : players) {
            if (player.getWaiting()){flag = false;}
        }
        return flag;
    }

    public void nextTurn(){
        currentTurn += 1;
        if (currentTurn > numPlayers){currentTurn = 1;}
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

    //api call to add new player to the game
    public String joinGame(String name){
        if (numPlayers > 3){
            return "";
        }

        //missing functionality: make sure there are no duplicate player names
        addPlayer(new Player(name));
        System.out.println("player "+name+" has joined the game"); //debug message
        return name;
        

    }

    //api call to set player as ready to start the game
    public boolean startGame(String name){
        //set player as ready to start, if all players are ready give them all the “ALL_PLAYERS_READY” signal
        getPlayer(name).setWaiting(false);
        if (allPlayersReady()){
            for (Player player : players) {
                player.eventQueue.add("ALL_PLAYERS_READY");
                for(int i = 0; i < 12; i++){
                    player.addCardToHand(adventuredeck.draw());
                }
            }
            state = "turn_start"; //set the internal state to begin the game
        }
        return true;
    }

    //api call to get a player's list of update signals
    public ArrayList<String> getUpdates(String name){
        return getPlayer(name).sendEventQueue();
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
