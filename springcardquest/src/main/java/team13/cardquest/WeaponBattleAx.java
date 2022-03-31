package team13.cardquest;

public class WeaponBattleAx implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Battle Ax", 15));
	}
}