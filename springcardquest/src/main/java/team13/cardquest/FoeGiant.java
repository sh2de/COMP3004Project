package team13.cardquest;

public class FoeGiant implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Giant", 40, 0));
	}
}