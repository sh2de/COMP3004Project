package team13.cardquest;

public class WeaponExcalibur implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Excalibur", 10));
	}
}