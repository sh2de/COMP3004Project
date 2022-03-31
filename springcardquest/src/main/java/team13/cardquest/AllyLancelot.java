package team13.cardquest;

public class AllyLancelot implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Sir Lancelot", 15, 1));
	}
}