package team13.cardquest;

public class AllyGuinevere implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Queen Guinevere", 0, 3));
	}
}