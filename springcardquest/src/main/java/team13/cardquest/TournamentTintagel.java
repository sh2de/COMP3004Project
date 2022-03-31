package team13.cardquest;

public class TournamentTintagel implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTournament(new BlobTournament("Tournament at Tintagel", 1));
	}
}