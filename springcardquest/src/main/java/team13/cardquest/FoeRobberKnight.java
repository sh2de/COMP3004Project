package team13.cardquest;

public class FoeRobberKnight implements Playable{
    public void OnPlay(Game game){
		game.ReceiveFoe(new BlobFoe("Robber Knight", 15, 0));
	}
}