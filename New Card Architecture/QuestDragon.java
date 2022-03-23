public class QuestDragon implements Playable{
	public void OnPlay(Game game){
		game.ReceiveQuest(new BlobQuest("Slay the Dragon", 3, "Dragon"));
	}
}