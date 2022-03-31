package team13.cardquest;

public class AllyGalahad implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Sir Galahad", 15, 1));
	}
}