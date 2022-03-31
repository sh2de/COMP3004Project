package team13.cardquest;

public class TournamentYork implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTournament(new BlobTournament("Tournament at York", 0));
	}
}