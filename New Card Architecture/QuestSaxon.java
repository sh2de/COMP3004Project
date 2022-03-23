public class QuestSaxons implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Repel the Saxon Raiders", 2, "Saxon"));
	}
}