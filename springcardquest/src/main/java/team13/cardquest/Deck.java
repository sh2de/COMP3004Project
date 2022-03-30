package team13.cardquest;

import java.util.ArrayList;


public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public void AddCard(Card card){cards.add(card);}

    public void PrintDeck(){
        for(int i = 0; i<cards.size(); i++){
            System.out.println(cards.get(i).getName());
        }
    }

    public Card draw(){
        //removes and returns a random card from the deck
        //if (stack.size() == 0){initialize();}
        
        int x = (int) (Math.random() * cards.size());

        return cards.remove(x);
    }

}
