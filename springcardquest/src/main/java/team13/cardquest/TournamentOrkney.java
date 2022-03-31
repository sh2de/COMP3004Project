package team13.cardquest;

public class TournamentOrkney implements Playable{
	public void OnPlay(Game game){
		game.ReceiveTournament(new BlobTournament("Tournament at Orkney", 2));
	}
}