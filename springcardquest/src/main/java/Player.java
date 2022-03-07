import java.util.ArrayList;

public class Player {
    public ArrayList<Card> hand = new ArrayList<Card>();
    public int shields = 0;
    public String rank = "squire";

    public void editShields(int s){
        shields += s;
        //check for rank up/down/win
    }

}
