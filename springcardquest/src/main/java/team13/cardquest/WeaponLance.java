package team13.cardquest;

public class WeaponLance implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Lance", 20));
	}
}