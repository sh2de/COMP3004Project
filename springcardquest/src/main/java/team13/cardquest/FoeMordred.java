package team13.cardquest;

public class FoeMordred implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Mordred", 30, 0));
	}
}