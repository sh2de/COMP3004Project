package team13.cardquest;

public class AdventureCard extends Card{
    
    public static int idCounter = 0;
    //public long id = (long) idCounter++;

    public AdventureCard(){
        id = (long) idCounter++;
    }

    @Override
    public void play() {

    }
}
