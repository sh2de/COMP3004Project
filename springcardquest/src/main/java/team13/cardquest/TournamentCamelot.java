package team13.cardquest;

public class TournamentCamelot implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTournament(new BlobTournament("Tournament at Camelot", 3));
	}
}