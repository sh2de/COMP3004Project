package team13.cardquest;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int shields = 0;
    private String rank = "squire";
    private String name = "";
    private Boolean waiting = true; //this variable determines if the player is waiting for other input
    private Boolean alive = false; //this variable is used in quests or tournaments to judge if the player is a participant or not
    private ArrayList<String> eventQueue = new ArrayList<>();
    private ArrayList<BlobAlly> allies = new ArrayList<>();
    private ArrayList<Card> playableHand = new ArrayList<>(); //this arraylist will temporarily hold the cards played by a player
    

    public Player(String _name){
        name = _name;
    }

    public void addEventSignal(String s){
        eventQueue.add(s);
    }


    public ArrayList<String> sendEventQueue(){ //sends the top event from the event queue
        //return eventQueue;
//        if (eventQueue.size() == 0){return "";}
//        else {return eventQueue.remove(0);}
        ArrayList<String>updates = eventQueue;
        eventQueue = new ArrayList<String>();
        return updates;
    }

    //this function gets called by the gamehandler and should send an event to the connected player
    public void updateGui(String command){
        //for now, simply println according to the command for test purposes
        System.out.println("PLAYER "+name+" HAS RECEIVED COMMAND "+command);

    }

    public void editShields(int s){
        shields += s;
        if (shields < 0){shields = 0;}
        //check for rank up/down/win
        if (shields >= 5 || rank.equals("squire")){rank = "knight";}
        if (shields >= 7 || rank.equals("knight")){rank = "champion knight";}
        if (shields >= 10 || rank.equals("champion knight")){rank = "knight of the round table";}
    }

    public void addCardToHand(Card c){
        hand.add(c);
        if(hand.size()>12){
            //if hand is bigger than 12, player must discard a card
            //test version, discard first card
            //actual version should prompt player to pick a card to discard
            hand.remove(0);
        }
    }

    public void removeCardByName(String n){
        Card toRemove = null;
        Boolean flag = false;
        for (Card c : hand) {
            if (c.getName().equals(n)){
                flag = true;
                toRemove = c;
                break;
            }
        }
        if (flag){hand.remove(toRemove);}
    }

    //check if this player is capable of sponsoring a given quest with their current hand
    public boolean canSponsor(BlobQuest q){
        int foes = 0;
        boolean test = false; //we only check for one because only one test may be played per quest

        for (Card c : hand) {
            if (c.getType().equals("FOE")){foes++;}
            if (c.getType().equals("TEST")){test = true;}
        }

        if (test){foes++;}

        if (foes >= q.stages){return true;}

        return false;
    }

    //playable hand functions
    public void setPlayableHand(ArrayList<Card> playableHand) {
        this.playableHand = playableHand;
    }

    public ArrayList<Card> getPlayableHand() {
        return playableHand;
    }

    public ArrayList<Card> discardPlayableHand(){//returns the list so it can be properly discarded in the game object
        ArrayList<Card> discards = playableHand;
        playableHand = new ArrayList<>();
        return discards;
    }

    public void print(){
        System.out.println("Player has "+shields+" shields");
        System.out.println("Player has the rank of "+rank);
        System.out.print("Player has the cards: | ");
        for (Card card : hand) {
            System.out.print(card+" | ");
        }
        System.out.println("");
        System.out.print("Player is ");
        if (!waiting){System.out.print("not ");}
        System.out.print("waiting for input\n");

    }

    public String getName(){
        return name;
    }

    public boolean getWaiting(){
        return waiting;
    }

    public int getShields(){
        return shields;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public ArrayList<BlobAlly> getAllies(){
        return allies;
    }

    public ArrayList<BlobAlly> discardAllAllies(){
        //missing: send allies to discard pile
        ArrayList<BlobAlly> discards = allies;
        allies = new ArrayList<>();
        return discards;
    }

    public String getRank(){
        return rank;
    }

    public boolean getAlive(){
        return alive;
    }

    public void setAlive(boolean b){
        alive = b;
    }

    public int getPower(){
        int p = 0;
        switch(rank){
            case "squire":
                p+=5;
                break;
            case "knight":
                p+=10;
                break;
            case "champion knight":
                p+=20;
                break;
            case "knight of the round table":
                p+=20;
                break;
        }
        //add power for allies
        for (BlobAlly blobAlly : allies) {
            p += blobAlly.power;
        }

        return p;
    }

    public void setWaiting(boolean _waiting){
        waiting = _waiting;
    }

}
