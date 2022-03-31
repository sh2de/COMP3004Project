package team13.cardquest;

public class WeaponSword implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Sword", 10));
	}
}