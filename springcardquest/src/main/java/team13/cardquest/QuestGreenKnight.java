package team13.cardquest;

public class QuestGreenKnight implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Test of the Green Knight", 4, "Green Knight"));
	}
}