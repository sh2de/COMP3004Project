package team13.cardquest;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int shields = 0;
    private String rank = "squire";
    private String name = "";
    private Boolean waiting = true; //this variable determines if the player is waiting for other input
    public ArrayList<String> eventQueue = new ArrayList<String>();


    public Player(String _name){
        name = _name;
    }

    public ArrayList<String> sendEventQueue(){
        ArrayList<String> updates = eventQueue;
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

    //check if this player is capable of sponsoring a given quest with their current hand
    public boolean canSponsor(Quest q){
        int foes = 0;
        boolean test = false; //we only check for one because only one test may be played per quest

        for (Card c : hand) {
            if (c.GetType().equals("FOE")){foes++;}
            if (c.GetType().equals("TEST")){test = true;}
        }

        if (test){foes++;}

        if (foes >= q.getStages()){return true;}

        return false;
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
        }
        //add power for allies

        return p;
    }

    public void setWaiting(boolean _waiting){
        waiting = _waiting;
    }

}
