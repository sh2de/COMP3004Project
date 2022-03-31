package team13.cardquest;

public class EventKingsRecognition implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("King's Recognition"));
	}
}