package team13.cardquest;

public class WeaponGeneric implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Generic", 0));
	}
}
