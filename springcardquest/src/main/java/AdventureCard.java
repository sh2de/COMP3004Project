public abstract class AdventureCard extends Card{
    
    public static int idCounter = 0;
    //public long id = (long) idCounter++;

    public AdventureCard(){
        id = (long) idCounter++;
    }
    
}
