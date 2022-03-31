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
        String header = "http://localhost:8080/images/";

        if(mode){
            addQuests(deck, header);
            //addEvents(deck, header);
            //addTournaments(deck, header);
        }else{
            //addAllies(deck, header);
            addFoes(deck, header);
            addWeapons(deck, header);
            //addArmours(deck, header);
            //addTests(deck, header);
        }

        return deck;
    }

    private void addQuests(Deck deck, String header){
        deck.AddCard(new Card("Search for the Holy Grail", "QUEST", 1, header+"Quest9.png", game, new QuestGrail()));
        deck.AddCard(new Card("Test of the Green Knight", "QUEST", 1, header+"Quest10.png", game, new QuestGreenKnight()));
        deck.AddCard(new Card("Search for the Questing Beast", "QUEST", 1, header+"Quest5.png", game, new QuestBeast()));
        deck.AddCard(new Card("Defend the Queen's Honor", "QUEST", 1, header+"Quest6.png", game, new QuestQueen()));
        deck.AddCard(new Card("Rescue the Fair Maiden", "QUEST", 1, header+"Quest8.png", game, new QuestMaiden()));
        deck.AddCard(new Card("Journey through the Enchanted Forest", "QUEST", 1, header+"Quest1.png", game, new QuestForest()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Vanquish King Arthur's Enemies", "QUEST", 1, header+"Quest2.png", game, new QuestArthur()));
        deck.AddCard(new Card("Slay the Dragon", "QUEST", 1, header+"Quest7.png", game, new QuestDragon()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Boar Hunt", "Quest", 1, header+"Quest4.png", game, new QuestBoar()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Repel the Saxon Raiders", "QUEST", 1, header+"Quest3.png", game, new QuestSaxons()));
    }

    private void addEvents(Deck deck, String header){
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("King's Recognition", "EVENT", 1, header+"King's_Recognition.png", game, new EventKingsRecognition()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Queen's Favor", "EVENT", 1, header+"Queen's_Favor.png", game, new EventQueensFavour()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Court Called to Camelot", "EVENT", 1, header+"Court_Called_to_Camelot.png", game, new EventCourtCalled()));
        deck.AddCard(new Card("Pox", "EVENT", 1, header+"Pox.png", game, new EventPox()));
        deck.AddCard(new Card("Plague", "EVENT", 1, header+"Plague.png", game, new EventPlague()));
        deck.AddCard(new Card("Chivalrous Deed", "EVENT", 1, header+"Chivalrous_Deed.png", game, new EventChivalrousDeed()));
        deck.AddCard(new Card("Prosperity Throughout the Realm", "EVENT", 1, header+"Prosperity_Throughout_the_Realm.png", game, new EventProsperity()));
    }

    private void addTournaments(Deck deck, String header){
        deck.AddCard(new Card("Tournament at Camelot", "TOURNAMENT", 1, header+"tournament1.png", game, new TournamentCamelot()));
        deck.AddCard(new Card("Tournament at Orkney", "TOURNAMENT", 1, header+"tournament2.png", game, new TournamentOrkney()));
        deck.AddCard(new Card("Tournament at Tintagel", "TOURNAMENT", 1, header+"tournament3.png", game, new TournamentTintagel()));
        deck.AddCard(new Card("Tournament at York", "TOURNAMENT", 1, header+"tournament4.png", game, new TournamentYork()));
    }

    private void addWeapons(Deck deck, String header){
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Excalibur", "WEAPON", 1, header+"excalibur.png", game, new WeaponExcalibur()));
        for(int i = 0; i<6; i++)
        deck.AddCard(new Card("Lance", "WEAPON", 1, header+"lance.png", game, new WeaponLance()));
        for(int i = 0; i<8; i++)
        deck.AddCard(new Card("Battle Ax", "WEAPON", 1, header+"battle_ax.png", game, new WeaponBattleAx()));
        for(int i = 0; i<16; i++)
        deck.AddCard(new Card("Sword", "WEAPON", 1, header+"sword.png", game, new WeaponSword()));
        for(int i = 0; i<11; i++)
        deck.AddCard(new Card("Horse", "WEAPON", 1, header+"horse.png", game, new WeaponHorse()));
        for(int i = 0; i<6; i++)
        deck.AddCard(new Card("Dagger", "WEAPON", 1, header+"dagger.png", game, new WeaponDagger()));
    }

    private void addTests(Deck deck, String header){
        
    }

    private void addFoes(Deck deck, String header){
        deck.AddCard(new Card("Dragon", "FOE", 1, header+"Dragon.png", game, new FoeDragon()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Giant", "FOE", 1, header+"Giant.png", game, new FoeGiant()));
        for(int i = 0; i<4; i++)
        deck.AddCard(new Card("Mordred", "FOE", 1, header+"Mordred.png", game, new FoeMordred()));
        for(int i = 0; i<2; i++)
        deck.AddCard(new Card("Green Knight", "FOE", 1, header+"Green_Knight.png", game, new FoeGreenKnight()));
        for(int i = 0; i<3; i++)
        deck.AddCard(new Card("Black Knight", "FOE", 1, header+"Black_Knight.png", game, new FoeBlackKnight()));
        for(int i = 0; i<6; i++)
        deck.AddCard(new Card("Evil Knight", "FOE", 1, header+"Evil_Knight.png", game, new FoeEvilKnight()));
        for(int i = 0; i<8; i++)
        deck.AddCard(new Card("Saxon Knight", "FOE", 1, header+"Saxons_Knight.png", game, new FoeSaxonKnight()));
        for(int i = 0; i<7; i++)
        deck.AddCard(new Card("Robber Knight", "FOE", 1, header+"Robber_Knight.png", game, new FoeRobberKnight()));
        for(int i = 0; i<5; i++)
        deck.AddCard(new Card("Saxons", "FOE", 1, header+"Saxons.png", game, new FoeSaxons()));
        for(int i = 0; i<4; i++)
        deck.AddCard(new Card("Boar", "FOE", 1, header+"Boar.png", game, new FoeBoar()));
        for(int i = 0; i<8; i++)
        deck.AddCard(new Card("Thieves", "FOE", 1, header+"Thieves.png", game, new FoeThieves()));
    }

    private void addAllies(Deck deck, String header){
        deck.AddCard(new Card("Sir Galahad", "ALLY", 1, header+"ally10.png", game, new AllyGalahad()));
        deck.AddCard(new Card("Sir Lancelot", "ALLY", 1, header+"ally9.png", game, new AllyLancelot()));
        deck.AddCard(new Card("King Arthur", "ALLY", 2, header+"ally5.png", game, new AllyArthur()));
        deck.AddCard(new Card("Sir Tristan", "ALLY", 1, header+"ally4.png", game, new AllyTristan()));
        deck.AddCard(new Card("King Pellinore", "ALLY", 1, header+"ally2.png", game, new AllyPellinore()));
        deck.AddCard(new Card("Sir Gawain", "ALLY", 1, header+"ally1.png", game, new AllyGawain()));
        deck.AddCard(new Card("Sir Percival", "ALLY", 1, header+"ally3.png", game, new AllyPercival()));
        deck.AddCard(new Card("Queen Guinevere", "ALLY", 3, header+"ally6.png", game, new AllyGuinevere()));
        deck.AddCard(new Card("Queen Iseult", "ALLY", 2, header+"ally8.png", game, new AllyIseult()));
        for(int i = 0; i<10; i++)
        deck.AddCard(new Card("Merlin", "ALLY", 1, header+"ally7.png", game, new AllyMerlin()));
    }

    private void addArmours(Deck deck, String header){
        for(int i=0; i<8; i++)
            deck.AddCard(new Card("Armour", "ARMOUR", 1, header+"armour1.png", game, new ArmourGeneric()));
    }
}