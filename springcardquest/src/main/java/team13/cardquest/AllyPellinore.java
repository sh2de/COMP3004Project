package team13.cardquest;

public class AllyPellinore implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("King Pellinore", 10, 1));
	}
}