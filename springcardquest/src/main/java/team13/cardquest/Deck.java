package team13.cardquest;

import java.util.ArrayList;


public class Deck {
    public ArrayList<Card> stack = new ArrayList<Card>();

    private boolean isStory;

    public Card draw(){
        //removes and returns a random card from the deck
        if (stack.size() == 0){initialize();}
        
        int x = (int) (Math.random() * stack.size());

        return stack.remove(x);
    }

    public void initialize(){
        String url = "..\\..\\..\\resources\\static\\imagestestcard.png";
        if(isStory){
            for(int i = 0; i<100; i++){
                String name = "Quest " + i;
                String foe = "Boss " + i;
                stack.add(new QuestCard(i, url, name, i, foe, i));
            }
        }else{
            for(int i = 0; i<100; i++){
                boolean isMord = i % 2 == 0 ? true: false;
                String name = "Foe " + i;
                stack.add(new FoeCard(i, url, name, i, i, isMord));
            }
        }
    }

    //fetch and create all quest cards (sami)
    /*public void initializeStoryDeck(){

    }

    //fetch and create all foe and weapon cards (sami)
    public void initializeAdventureDeck(){
*/


    public Deck(boolean isStory
    ){
        this.isStory = isStory;
        initialize(); //just comment this out when youre done with getting the information
    }
}
