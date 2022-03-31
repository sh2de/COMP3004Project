package team13.cardquest;

public class EventQueensFavour implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("Queen's Favour"));
	}
}