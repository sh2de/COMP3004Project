package team13.cardquest;

public class Card{
	private String name;
	private String type;
	private int value;
	private String url;

	private Game game;
	private Playable play;

	public Card(String name, String type, int value, String url, Game game, Playable play){
		this.name = name;
		this.type = type;
		this.value = value;
		this.url = url;

		this.game = game;
		this.play = play;
	}

	public void play(){play.OnPlay(game);}

	public String getName(){return name;}
	public String getType(){return type;}
	public int getValue(){return value;}
	public String getUrl(){return url;}

	//public String toString(){return name + " " + type + " " + value + " " + url;}
	public String toString(){return name;}
}