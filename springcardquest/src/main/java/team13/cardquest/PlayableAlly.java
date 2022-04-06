package team13.cardquest;

public class PlayableAlly implements Playable{
    private CardAlly card;

	public PlayableAlly(CardAlly card){
		this.card = card;
	}

    
	public void OnPlay(){
		 Game.ReceiveAlly(new BlobAlly(card.getName(), card.getPower(), card.getValue(), card.getUrl()));
	}
}
