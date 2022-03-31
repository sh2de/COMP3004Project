package team13.cardquest;

public class FoeGreenKnight implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Green Knight", 25, 20));
	}
}