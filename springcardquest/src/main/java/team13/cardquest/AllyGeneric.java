package team13.cardquest;

public class AllyGeneric implements Playable{
    public void OnPlay(Game game){
		game.ReceiveAlly(new BlobAlly("Generic", 0, 1));
	}
}
