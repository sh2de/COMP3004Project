public class QuestBeast implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Search for the Questing Beast", 4, "none"));
	}
}