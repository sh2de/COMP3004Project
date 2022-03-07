import java.util.ArrayList;

public class Deck {
    public ArrayList<Card> stack = new ArrayList<Card>();

    public Deck(){
        for (int i = 0; i<100; i++){stack.add(new Card());}
    }
}
