package team13.cardquest;

public class FoeBoar implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Boar", 5, 10));
	}
}