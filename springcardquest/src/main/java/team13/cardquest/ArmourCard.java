package team13.cardquest;

public class ArmourCard extends Card{
	private int battlePower;

	public ArmourCard(String name, int battlePower, int bidWorth, String url){
		id = -2;
		type = "Armour";
		this.name = name;
		this.battlePower = battlePower;
		this.bidWorth = bidWorth;
		this.url = url;
	}

	public void play(){
		System.out.println("dummy play behaviour");
	}
}