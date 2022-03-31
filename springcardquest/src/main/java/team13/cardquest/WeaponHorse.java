package team13.cardquest;

public class WeaponHorse implements Playable{
    public void OnPlay(Game game){
		game.ReceiveWeapon(new BlobWeapon("Horse", 10));
	}
}