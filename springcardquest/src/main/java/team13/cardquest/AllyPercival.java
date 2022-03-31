package team13.cardquest;

public class AllyPercival implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Sir Percival", 5, 1));
	}
}