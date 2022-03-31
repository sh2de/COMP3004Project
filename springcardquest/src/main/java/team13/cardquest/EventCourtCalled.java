package team13.cardquest;

public class EventCourtCalled implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("Court Called to Camelot"));
	}
}