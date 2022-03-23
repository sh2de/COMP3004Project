public class QuestEvilKnight implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Journey through the Enchanted Forest", 3, "EvilKnight"));
	}
}