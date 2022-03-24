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

	public void Play(){play.OnPlay(game);}

	public String GetName(){return name;}
	public String GetType(){return type;}
	public int GetValue(){return value;}
	public String GetUrl(){return url;}

	public String toString(){return name + " " + type + " " + value + " " + url;}
}