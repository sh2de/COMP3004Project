package team13.cardquest;

public class ArmourGeneric implements Playable{
	public void OnPlay(Game game){
		game.ReceiveArmour(new BlobArmour(1, 10));
	}
}