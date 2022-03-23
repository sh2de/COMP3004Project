public class QuestDefault implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("default", 1, "none"));
	}
}