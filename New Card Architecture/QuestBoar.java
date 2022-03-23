public class QuestBoar implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Boar Hunt", 2, "Boar"));
	}
}