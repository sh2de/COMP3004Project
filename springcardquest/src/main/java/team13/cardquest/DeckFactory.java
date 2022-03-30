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
        String defUrl = "http://localhost:8080/images/testcard.png";
        String header = "http://localhost:8080/images/";

        if(mode){
            deck.AddCard(new Card("Search for the Holy Grail", "QUEST", 1, header+"Quest9,png", game, new QuestGrail()));
            //deck.AddCard(new Card("Test of the Green Knight", "Quest", 1, url, game, new QuestGrail())); this card isn't shown in the images folder, I have no idea what attributes it has
            deck.AddCard(new Card("Search for the Questing Beast", "QUEST", 1, header+"Quest5.png", game, new QuestBeast()));
            deck.AddCard(new Card("Defend the Queen's Honor", "QUEST", 1, header+"Quest6.png", game, new QuestQueen()));
            deck.AddCard(new Card("Rescue the Fair Maiden", "QUEST", 1, header+"Quest8.png", game, new QuestMaiden()));
            deck.AddCard(new Card("Journey through the Enchanted Forest", "QUEST", 1, header+"Quest1.png", game, new QuestForest()));
            deck.AddCard(new Card("Vanquish King Arthur's Enemies", "QUEST", 1, header+"Quest2.png", game, new QuestArthur()));
            deck.AddCard(new Card("Vanquish King Arthur's Enemies", "QUEST", 1, header+"Quest2.png", game, new QuestArthur()));
            deck.AddCard(new Card("Slay the Dragon", "QUEST", 1, header+"Quest7.png", game, new QuestDragon()));
            deck.AddCard(new Card("Boar Hunt", "Quest", 1, header+"Quest4.png", game, new QuestBoar()));
            deck.AddCard(new Card("Boar Hunt", "Quest", 1, header+"Quest4.png", game, new QuestBoar()));
            deck.AddCard(new Card("Repel the Saxon Raiders", "QUEST", 1, header+"Quest3.png", game, new QuestSaxons()));
            deck.AddCard(new Card("Repel the Saxon Raiders", "QUEST", 1, header+"Quest3.png", game, new QuestSaxons()));
        }else{
            for (int i = 0; i < 600; i++){
                /deck.AddCard(new Card("TestFoe", "FOE", 1, defUrl, game, new FoeGeneric()));
                deck.AddCard(new Card("TestWeapon", "WEAPON", 1, defUrl, game, new WeaponGeneric()));
                
                deck.AddCard(new Card("Sir Galahad", "ALLY", 1, header+"ally10.png", game, new AllyGalahad()));
                deck.AddCard(new Card("Sir Lancelot", "ALLY", 1, header+"ally9.png", game, new AllyLancelot()));
                deck.AddCard(new Card("King Arthur", "ALLY", 2, header+"ally5.png", game, new AllyArthur()));
                deck.AddCard(new Card("Sir Tristan", "ALLY", 1, header+"ally4.png", game, new AllyTristan()));
                deck.AddCard(new Card("King Pellinore", "ALLY", 1, header+"ally2.png", game, new AllyPellinore()));
                deck.AddCard(new Card("Sir Gawain", "ALLY", 1, header+"ally1.png", game, new AllyGawain()));
                deck.AddCard(new Card("Sir Percival", "ALLY", 1, header+"ally3.png", game, new AllyPercival()));
                deck.AddCard(new Card("Queen Guinevere", "ALLY", 3, header+"ally6.png", game, new AllyGuinevere()));
                deck.AddCard(new Card("Queen Iseult", "ALLY", 2, header+"ally8.png", game, new AllyIseult()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
                deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));


                
            }
        }

        return deck;
    }
}