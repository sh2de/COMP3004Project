package team13.cardquest;

public class TestQuestingBeast implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTest(new BlobTest("Test of the Questing Beast", 3));
	}
}