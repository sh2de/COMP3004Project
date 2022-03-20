package team13.cardquest;

public class FoeCard extends AdventureCard{
    String name = "DUMMY FOE";
    int power = 20;
    int bosspower = 30;
    boolean mordred = false;

    public FoeCard(long _id, String _url, String _name, int _power, int _bosspower, boolean _mordred){
        id = _id;
        url = _url;
        name = _name;
        power = _power;
        bosspower = _bosspower;
        mordred = _mordred;
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
	
}
