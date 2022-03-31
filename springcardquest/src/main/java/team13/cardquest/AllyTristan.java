package team13.cardquest;

public class AllyTristan implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Sir Tristan", 10, 1));
	}
}