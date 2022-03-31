package team13.cardquest;

public class EventKingsCall implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("King's Call to Arms"));
	}
}