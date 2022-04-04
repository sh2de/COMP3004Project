package team13.cardquest;

public class PlayableFoe implements Playable{
    private CardFoe card;

	public PlayableFoe(CardFoe card){
		this.card = card;
	}

    
	public void OnPlay(){
		 card.getGame().ReceiveFoe(new BlobFoe(card.getName(), card.getPower(), card.getBoost()));
	}
}
