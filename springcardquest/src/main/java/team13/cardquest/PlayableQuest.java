package team13.cardquest;

public class PlayableQuest implements Playable{
    private CardQuest card;

	public PlayableQuest(CardQuest card){
		this.card = card;
	}

    
	public void OnPlay(){
		 card.getGame().ReceiveQuest(new BlobQuest(card.getName(), card.getStages(), card.getNamedFoe()));
	}
}
