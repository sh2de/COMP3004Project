package team13.cardquest;

public class QuestMaiden implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Rescue the Fair Maiden", 3, "Black Knight"));
	}
}