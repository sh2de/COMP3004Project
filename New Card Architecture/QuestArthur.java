public class QuestArthur implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Vanquish king Arthur's Enemies", 3, "none"));
	}
}