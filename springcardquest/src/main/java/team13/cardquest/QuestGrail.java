package team13.cardquest;

public class QuestGrail implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Search for the Holy Grail", 5, "all"));
	}
}