package team13.cardquest;

public abstract class Card{
	private String name;
	private String type;
	private int value;
	private String url;

	//protected Game game;
	private Playable play;


	//this is the constructor that the DeckFactory is using, the other constructor is outdated
	public Card(String name, String type, int value, String url){
		this.name = name;
		this.type = type;
		this.value = value;
		this.url = url;

		//this.game = game;
	}

	public void play(){play.OnPlay();}

	public String getName(){return name;}
	public String getType(){return type;}
	public int getValue(){return value;}
	public String getUrl(){return url;}
	//public Game getGame(){return game;}
	public Playable getPlay(){return play;}

	public void setPlay(Playable p){play = p;}

	public String toString(){return name + " " + type + " " + value + " " + url;}
}