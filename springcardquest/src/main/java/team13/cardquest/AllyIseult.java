package team13.cardquest;

public class AllyIseult implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Queen Iseult", 0, 2));
	}
}