package team13.cardquest;

public abstract class Card{
	protected String name;
	protected String type;
	protected int value;
	protected String url;

	protected Game game;
	protected Playable play;

	public Card(String name, String type, int value, String url, Game game, Playable play){
		this.name = name;
		this.type = type;
		this.value = value;
		this.url = url;

		this.game = game;
		this.play = play;
	}


	//this is the constructor that the DeckFactory is using, the other constructor is outdated
	public Card(String name, String type, int value, String url, Game game){
		this.name = name;
		this.type = type;
		this.value = value;
		this.url = url;

		this.game = game;
	}

	public void play(){play.OnPlay();}

	public String getName(){return name;}
	public String getType(){return type;}
	public int getValue(){return value;}
	public String getUrl(){return url;}
	public Game getGame(){return game;}
	//public Playable getPlay(){return play;}

	public String toString(){return name + " " + type + " " + value + " " + url;}
}