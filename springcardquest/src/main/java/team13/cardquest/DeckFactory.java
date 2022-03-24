package team13.cardquest;

public class DeckFactory{
	    
    private Game game;
    
    public DeckFactory(Game game){
        this.game = game;
    }
    
    /**
     * 
     * @param mode true for story deck, false for adventure deck
     */
    public Deck CreateDeck(boolean mode){
        Deck deck = new Deck();
        String url = "http://localhost:8080/images/testcard.png";

        if(mode){
            deck.AddCard(new Card("Search for the Holy Grail", "QUEST", 1, url, game, new QuestGrail()));
            //deck.AddCard(new Card("Test of the Green Knight", "Quest", 1, url, game, new QuestGrail())); this card isn't shown in the images folder, I have no idea what attributes it has
            deck.AddCard(new Card("Search for the Questing Beast", "QUEST", 1, url, game, new QuestBeast()));
            deck.AddCard(new Card("Defend the Queen's Honor", "QUEST", 1, url, game, new QuestQueen()));
            deck.AddCard(new Card("Search for the Holy Grail", "QUEST", 1, url, game, new QuestGrail()));
            deck.AddCard(new Card("Rescue the Fair Maiden", "QUEST", 1, url, game, new QuestMaiden()));
            deck.AddCard(new Card("Journey through the Enchanted Forest", "QUEST", 1, url, game, new QuestForest()));
            deck.AddCard(new Card("Vanquish King Arthur's Enemies", "QUEST", 1, url, game, new QuestArthur()));
            deck.AddCard(new Card("Vanquish King Arthur's Enemies", "QUEST", 1, url, game, new QuestArthur()));
            deck.AddCard(new Card("Slay the Dragon", "QUEST", 1, url, game, new QuestDragon()));
            deck.AddCard(new Card("Boar Hunt", "Quest", 1, url, game, new QuestBoar()));
            deck.AddCard(new Card("Boar Hunt", "Quest", 1, url, game, new QuestBoar()));
            deck.AddCard(new Card("Repel the Saxon Raiders", "QUEST", 1, url, game, new QuestSaxons()));
            deck.AddCard(new Card("Repel the Saxon Raiders", "QUEST", 1, url, game, new QuestSaxons()));
        }else{
            System.out.println("Can't make adventure decks yet :(");
        }

        return deck;
    }
}