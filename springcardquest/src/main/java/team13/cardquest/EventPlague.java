package team13.cardquest;

public class EventPlague implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("Plague"));
	}
}