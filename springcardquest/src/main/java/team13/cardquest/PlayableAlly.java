package team13.cardquest;

public class PlayableAlly implements Playable{
    private CardAlly card;

	public PlayableAlly(CardAlly card){
		this.card = card;
	}

    
	public void OnPlay(){
		 card.getGame().ReceiveFoe(new BlobFoe(card.getName(), card.getPower(), card.getValue()));
	}
}
