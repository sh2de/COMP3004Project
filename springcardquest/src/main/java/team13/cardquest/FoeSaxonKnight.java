package team13.cardquest;

public class FoeSaxonKnight implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Saxon Knight", 15, 10));
	}
}