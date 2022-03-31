package team13.cardquest;

public class EventChivalrousDeed implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("Chivalrous Deed"));
	}
}