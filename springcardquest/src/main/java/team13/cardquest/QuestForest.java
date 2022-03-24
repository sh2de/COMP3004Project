package team13.cardquest;

public class QuestForest implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Journey through the Enchanted Forest", 3, "EvilKnight"));
	}
}