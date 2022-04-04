package team13.cardquest;

public class PlayableWeapon implements Playable{
    private CardWeapon card;

	public PlayableWeapon(CardWeapon card){
		this.card = card;
	}

    
	public void OnPlay(){
		 card.getGame().ReceiveWeapon(new BlobWeapon(card.getName(), card.getPower()));
	}
}
