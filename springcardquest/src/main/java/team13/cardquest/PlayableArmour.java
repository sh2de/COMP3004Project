package team13.cardquest;

public class PlayableArmour implements Playable{
    private CardArmour card;

	public PlayableArmour(CardArmour card){
		this.card = card;
	}

    
	public void OnPlay(){
		 Game.ReceiveArmour(new BlobArmour(card.getValue(), card.getPower()));
	}
}
