package team13.cardquest;

public class FoeThieves implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Thieves", 5, 0));
	}
}