package team13.cardquest;

public class FoeCard extends Card{
    //spooky unencapsulation
    public boolean isMordred = false;
    private int battlePower, boost;
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
        return name + ", a foe with a power of " + battlePower + "/" + boost+" ";
    }

    public String getType(){
        return "FOE";
    }

    public int getPower(){
        return battlePower;
    }

    public void play(){
        System.out.println("dummy play behaviour");
    }
	
}
