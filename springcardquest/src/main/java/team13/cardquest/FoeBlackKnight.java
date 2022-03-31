package team13.cardquest;

public class FoeBlackKnight implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Black Knight", 25, 10));
	}
}