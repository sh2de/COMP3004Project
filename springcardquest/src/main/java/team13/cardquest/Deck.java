package team13.cardquest;

import java.util.ArrayList;


public class Deck {

    private ArrayList<Card> cards;
    private ArrayList<Card> discards;

    public Deck(){
        cards = new ArrayList<>();
        discards = new ArrayList<>();
    }

    public void AddCard(Card card){cards.add(card);}

    public void PrintDeck(){
        for(int i = 0; i<cards.size(); i++){
            System.out.println(cards.get(i).getName());
        }
    }

    public Card draw(){//removes and returns a random card from the deck
        
        //before drawing, refresh the deck if nessecary
        if (cards.size() == 0){
            cards = discards;
            discards = new ArrayList<>();
        }
        
        int x = (int) (Math.random() * cards.size());

        return cards.remove(x);
    }

    public void discard(Card c){
        discards.add(c);
    }

    public void discardList(ArrayList<Card> c){
        for (Card card : c) {
            discards.add(card); 
        }
    }

}
