package team13.cardquest;

public class TestCard extends Card{
	private int minBid;
	private String Trigger;

	public TestCard(String name, int minBid, String Trigger, int bidWorth, String url){
		id = -3;
		type = "Test";
		this.minBid = minBid;
		this.trigger = trigger;
		this bidWorth = bidWorth;
		this.url = url;
	}

	public void checkTrigger(){
		System.out.println("dummy trigger behaviour");
	}

	public void play(){
		System.out.println("dummy play behaviour");
	}
}