package team13.cardquest;

public class PlayableTournament implements Playable{
    private CardTournament card;

	public PlayableTournament(CardTournament card){
		this.card = card;
	}

    
	public void OnPlay(){
		 card.getGame().ReceiveTournament(new BlobTournament(card.getName(), card.getShields()));
	}
}
