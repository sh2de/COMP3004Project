package team13.cardquest;

public class PlayableTest implements Playable{
    private CardTest card;

	public PlayableTest(CardTest card){
		this.card = card;
	}

    
	public void OnPlay(){
		 Game.ReceiveTest(new BlobTest(card.getName(), card.getMinBid()));
	}
}
