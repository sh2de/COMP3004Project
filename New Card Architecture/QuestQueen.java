public class QuestQueen implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Defend the Queen's Honor", 4, "all"));
	}
}