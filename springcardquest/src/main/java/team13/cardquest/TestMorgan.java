package team13.cardquest;

public class TestMorgan implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTest(new BlobTest("Test of Morgan Le Fey", 3));
	}
}