package team13.cardquest;

public class FoeSaxons implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Saxons", 10, 10));
	}
}