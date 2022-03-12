import java.util.ArrayList;

public class Player {
    public ArrayList<Card> hand = new ArrayList<Card>();
    public int shields = 0;
    public String rank = "squire";
    public String name = "";
    public Boolean waiting = true; //this variable determines if the player is waiting for other input

    public Player(String _name){
        name = _name;
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

    public void print(){
        System.out.println("Player has "+shields+" shields");
        System.out.println("Player has the rank of "+rank);
        System.out.print("Player has the cards: |");
        for (Card card : hand) {
            System.out.print(card.id +"|");
        }
        System.out.println("");

    }

    public String getName(){
        return name;
    }

    public boolean getWaiting(){
        return waiting;
    }

    public void setWaiting(boolean _waiting){
        waiting = _waiting;
    }

}
