package team13.cardquest;

public class FoeGeneric implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Generic", 0, 10));
	}
}
