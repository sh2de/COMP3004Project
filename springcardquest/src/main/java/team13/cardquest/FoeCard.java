package team13.cardquest;

public class FoeCard extends Card{
    //spooky unencapsulation
    public boolean isMordred = false;

    public FoeCard(String name, int battlePower, int boost, int bidWorth, String url){
        id = -3;
        type = "Foe";
        this.name = name;
        this.battlePower = battlePower;
        this.boost = boost;
        this.bidWorth = bidWorth;
        this.url = url;
        
    }

    public String toString(){
        return name + ", a foe with a power of " + power + "/" + bosspower+" ";
    }

    public String getType(){
        return "FOE";
    }

    public int getPower(){
        return power;
    }

    public void play(){
        System.out.println("dummy play behaviour");
    }
	
}
