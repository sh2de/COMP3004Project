package team13.cardquest;

public class EventProsperity implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("Prosperity Throughout the Realm"));
	}
}