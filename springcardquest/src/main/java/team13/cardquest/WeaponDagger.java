package team13.cardquest;

public class WeaponDagger implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Dagger", 5));
	}
}