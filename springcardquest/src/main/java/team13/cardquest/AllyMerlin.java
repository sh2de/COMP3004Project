package team13.cardquest;

public class AllyMerlin implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Merlin", 0, 1));
	}
}