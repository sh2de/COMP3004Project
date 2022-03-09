import java.util.ArrayList;

public class Player {
    public ArrayList<Card> hand = new ArrayList<Card>();
    public int shields = 0;
    public String rank = "squire";

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

}
