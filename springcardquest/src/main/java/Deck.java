import java.util.ArrayList;


public class Deck {
    public ArrayList<Card> stack = new ArrayList<Card>();


    public Card draw(){
        //removes and returns a random card from the deck
        if (stack.size() == 0){shuffle();}
        
        int x = (int) (Math.random() * stack.size());

        return stack.remove(x);
    }

    public void shuffle(){
        for (int i = 0; i<100; i++){stack.add(new FoeCard(i, null, null, i, i, false));}
    }

    //fetch and create all quest cards (sami)
    public void shuffleStoryDeck(){

    }

    //fetch and create all foe and weapon cards (sami)
    public void shuffleAdventureDeck(){

    }

    public Deck(){
        shuffle(); //just comment this out when youre done with getting the information
    }
}
