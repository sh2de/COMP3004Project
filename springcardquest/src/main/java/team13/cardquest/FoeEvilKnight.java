package team13.cardquest;

public class FoeEvilKnight implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Evil Knight", 20, 10));
	}
}