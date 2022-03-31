package team13.cardquest;

public class EventPox implements Playable{
    public void OnPlay(Game game){
		game.ReceiveEvent(new BlobEvent("Pox"));
	}
}