package team13.cardquest;

public class AllyCard extends Card{
	//triggers will become "strategic" later on
	private String trigger;

	public AllyCard(String name, int bidWorth, String url, String trigger){
		id = -1;
		type = "Ally";
		this.name = name;
		this.bidWorth = bidWorth;
		this.url = url;
		this.trigger = trigger;
	}

	public void checkTrigger(){
		System.out.println("dummy trigger behaviour");
	}

	public void play(){
		System.out.println("dummy play behaviour");
	}
}