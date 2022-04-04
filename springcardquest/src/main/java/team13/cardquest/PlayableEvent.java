package team13.cardquest;

public class PlayableEvent implements Playable{
    private CardEvent card;

    public PlayableEvent(CardEvent card){
        this.card = card;
    }
    
    public void OnPlay() {
        card.getGame().ReceiveEvent(new BlobEvent(card.getName()));
    }
    
}
