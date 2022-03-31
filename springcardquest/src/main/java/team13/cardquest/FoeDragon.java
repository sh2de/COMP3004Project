package team13.cardquest;

public class FoeDragon implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Dragon", 50, 20));
	}
}