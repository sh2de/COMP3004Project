package team13.cardquest;

public class DeckFactory{
	    
    private Game game;
    private String header;
    
    public DeckFactory(Game game){
        this.game = game;
        header = "http://localhost:8080/images/";
    }
    
    /**
     * 
     * @param mode true for story deck, false for adventure deck
     */
    public Deck CreateDeck(boolean mode){
        Deck deck = new Deck();

        if(mode){
            addQuests(deck);
            addEvents(deck);
            //addTournaments(deck);
        }else{
            addAllies(deck);
            addFoes(deck);
            addWeapons(deck);
            //addArmours(deck);
            //addTests(deck);
        }

        return deck;
    }

    private void addQuests(Deck deck){
        String type = "QUEST";
        int value = 1;

        deck.AddCard(new CardQuest("Search for the Holy Grail", type, value, header+"Quest9.png", 5, "All"));
        deck.AddCard(new CardQuest("Test of the Green Knight", type, value, header+"Quest10.png", 4, "Green Knight"));
        deck.AddCard(new CardQuest("Search for the Questing Beast", type, value, header+"Quest5.png", 4, "None"));
        deck.AddCard(new CardQuest("Defend the Queen's Honor", type, value, header+"Quest6.png", 4, "All"));
        deck.AddCard(new CardQuest("Rescue the Fair Maiden", type, value, header+"Quest8.png", 3, "Black Knight"));
        deck.AddCard(new CardQuest("Journey through the Enchanted Forest", type, value, header+"Quest1.png", 3, "Evil Knight"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardQuest("Vanquish King Arthur's Enemies", type, value, header+"Quest2.png", 3, "None"));
        deck.AddCard(new CardQuest("Slay the Dragon", type, value, header+"Quest7.png", 3, "Dragon"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardQuest("Boar Hunt", type, value, header+"Quest4.png", 2, "Boar"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardQuest("Repel the Saxon Raiders", type, value, header+"Quest3.png", 2, "Saxon"));
    }

    private void addEvents(Deck deck){
        String type = "EVENT";
        int value = 1;

        for(int i = 0; i<2; i++) deck.AddCard(new CardEvent("King's Recognition", type, value, header+"King's_Recognition.png"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardEvent("Queen's Favor", type, value, header+"Queen's_Favor.png"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardEvent("Court Called to Camelot", type, value, header+"Court_Called_to_Camelot.png"));
        deck.AddCard(new CardEvent("Pox", type, value, header+"Pox.png"));
        deck.AddCard(new CardEvent("Plague", type, value, header+"Plague.png"));
        deck.AddCard(new CardEvent("Chivalrous Deed", type, value, header+"Chivalrous_Deed.png"));
        deck.AddCard(new CardEvent("Prosperity Throughout the Realm", type, value, header+"Prosperity_Throughout_the_Realm.png"));
    }

    private void addTournaments(Deck deck){
        String type = "TOURNAMENT";
        int value = 1;
        String tat = "Tournament at ";
        
        deck.AddCard(new CardTournament(tat+"Camelot", type, value, header+"tournament1.png", 3));
        deck.AddCard(new CardTournament(tat+"Orkney", type, value, header+"tournament2.png", 2));
        deck.AddCard(new CardTournament(tat+"Tintagel", type, value, header+"tournament3.png", 1));
        deck.AddCard(new CardTournament(tat+"York", type, value, header+"tournament4.png", 0));
    }

    private void addWeapons(Deck deck){
        String type = "WEAPON";
        int value = 1;

        for(int i = 0; i<2; i++) deck.AddCard(new CardWeapon("Excalibur", type, value, header+"excalibur.png", 10));
        for(int i = 0; i<6; i++) deck.AddCard(new CardWeapon("Lance", type, value, header+"lance.png", 20));
        for(int i = 0; i<8; i++) deck.AddCard(new CardWeapon("Battle Ax", type, value, header+"battle_ax.png", 15));
        for(int i = 0; i<16; i++) deck.AddCard(new CardWeapon("Sword", type, value, header+"sword.png", 10));
        for(int i = 0; i<11; i++) deck.AddCard(new CardWeapon("Horse", type, value, header+"horse.png", 10));
        for(int i = 0; i<6; i++) deck.AddCard(new CardWeapon("Dagger", type, value, header+"dagger.png", 5));
    }

    private void addTests(Deck deck){
        String type = "TEST";
        int value = 1;

        for(int i = 0; i<2; i++){
            deck.AddCard(new CardTest("Test of Valor", type, value, header+"test3.png", 3));
            deck.AddCard(new CardTest("Test of Temptation", type, value, header+"test2.png", 3));
            deck.AddCard(new CardTest("Test of Morgan Le Fey", type, value, header+"test4.png", 3));
            deck.AddCard(new CardTest("Test of the Questing Beast", type, value, header+"test1.png", 3));
        }
    }

    private void addFoes(Deck deck){
        String type = "FOE";
        int value = 1;

        deck.AddCard(new CardFoe("Dragon", type, value, header+"Dragon.png", 50, 20));
        for(int i = 0; i<2; i++) deck.AddCard(new CardFoe("Giant", type, value, header+"Giant.png", 40, 0));
        for(int i = 0; i<4; i++) deck.AddCard(new CardFoe("Mordred", type, value, header+"Mordred.png", 30, 0));
        for(int i = 0; i<2; i++) deck.AddCard(new CardFoe("Green Knight", type, value, header+"Green_Knight.png", 25, 20));
        for(int i = 0; i<3; i++) deck.AddCard(new CardFoe("Black Knight", type, value, header+"Black_Knight.png", 25, 10));
        for(int i = 0; i<6; i++) deck.AddCard(new CardFoe("Evil Knight", type, value, header+"Evil_Knight.png", 20, 10));
        for(int i = 0; i<8; i++) deck.AddCard(new CardFoe("Saxon Knight", type, value, header+"Saxons_Knight.png", 15, 10));
        for(int i = 0; i<7; i++) deck.AddCard(new CardFoe("Robber Knight", type, value, header+"Robber_Knight.png", 15, 0));
        for(int i = 0; i<5; i++) deck.AddCard(new CardFoe("Saxons", type, value, header+"Saxons.png", 10, 10));
        for(int i = 0; i<4; i++) deck.AddCard(new CardFoe("Boar", type, value, header+"Boar.png", 5, 10));
        for(int i = 0; i<8; i++) deck.AddCard(new CardFoe("Thieves", type, value, header+"Thieves.png", 5, 0));
    }

    private void addAllies(Deck deck){
        String type = "ALLY";

        deck.AddCard(new CardAlly("Sir Galahad", type, 1, header+"ally10.png", 15));
        deck.AddCard(new CardAlly("Sir Lancelot", type, 1, header+"ally9.png", 15));
        deck.AddCard(new CardAlly("King Arthur", type, 2, header+"ally5.png", 10));
        deck.AddCard(new CardAlly("Sir Tristan", type, 1, header+"ally4.png", 10));
        deck.AddCard(new CardAlly("King Pellinore", type, 1, header+"ally2.png", 10));
        deck.AddCard(new CardAlly("Sir Gawain", type, 1, header+"ally1.png", 10));
        deck.AddCard(new CardAlly("Sir Percival", type, 1, header+"ally3.png", 5));
        deck.AddCard(new CardAlly("Queen Guinevere", type, 3, header+"ally6.png", 0));
        deck.AddCard(new CardAlly("Queen Iseult", type, 2, header+"ally8.png", 0));
        for(int i = 0; i<10; i++) deck.AddCard(new CardAlly("Merlin", type, 1, header+"ally7.png", 0));
    }

    private void addArmours(Deck deck){
        for(int i=0; i<8; i++) deck.AddCard(new CardArmour("armour", "ARMOUR", 1, header+"armour1.png", 10));
    }
}