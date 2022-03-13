package team13.cardquest;

public abstract class StoryCard extends Card{
    
    public static int idCounter = 0;
    //public long id = (long) idCounter++;

    public StoryCard(){
        id = (long) idCounter++;
    }
    
}
