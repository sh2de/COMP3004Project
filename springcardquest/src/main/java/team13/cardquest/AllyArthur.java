package team13.cardquest;

public class AllyArthur implements Playable{
	public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("King Arthur", 10, 1));
	}
}