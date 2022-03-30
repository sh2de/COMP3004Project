package team13.cardquest;

public class AllyGawain implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Sir Gawain", 10, 1));
	}
}