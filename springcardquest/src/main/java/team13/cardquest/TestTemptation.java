package team13.cardquest;

public class TestTemptation implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTest(new BlobTest("Test of Temptation", 3));
	}
}