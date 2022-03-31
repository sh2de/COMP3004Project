package team13.cardquest;

public class TestValor implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTest(new BlobTest("Test of Valor", 3));
	}
}