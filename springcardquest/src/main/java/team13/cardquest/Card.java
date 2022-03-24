package team13.cardquest;

<<<<<<< HEAD
public class Card{
	private String name;
	private String type;
	private int value;
	private String url;
=======
public abstract class Card {
    protected long id = -1;
    protected String name = "UNNAMED";
    protected String type = "UNDEFINED";
    protected int bidWorth = 1;
    protected String url = "./images/testcard.png";
    protected int frequency=0;
>>>>>>> 76af40816f7a19092c7c65a75227bb570e806728

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