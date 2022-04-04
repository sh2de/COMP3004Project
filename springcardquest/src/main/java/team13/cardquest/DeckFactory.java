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
            //addEvents(deck);
            //addTournaments(deck);
        }else{
            //addAllies(deck);
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

        deck.AddCard(new CardQuest("Search for the Holy Grail", type, value, header+"Quest9.png", game, 5, "All"));
        deck.AddCard(new CardQuest("Test of the Green Knight", type, value, header+"Quest10.png", game, 4, "Green Knight"));
        deck.AddCard(new CardQuest("Search for the Questing Beast", type, value, header+"Quest5.png", game, 4, "None"));
        deck.AddCard(new CardQuest("Defend the Queen's Honor", type, value, header+"Quest6.png", game, 4, "All"));
        deck.AddCard(new CardQuest("Rescue the Fair Maiden", type, value, header+"Quest8.png", game, 3, "Black Knight"));
        deck.AddCard(new CardQuest("Journey through the Enchanted Forest", type, value, header+"Quest1.png", game, 3, "Evil Knight"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardQuest("Vanquish King Arthur's Enemies", type, value, header+"Quest2.png", game, 3, "None"));
        deck.AddCard(new CardQuest("Slay the Dragon", type, value, header+"Quest7.png", game, 3, "Dragon"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardQuest("Boar Hunt", type, value, header+"Quest4.png", game, 2, "Boar"));
        for(int i = 0; i<2; i++) deck.AddCard(new CardQuest("Repel the Saxon Raiders", type, value, header+"Quest3.png", game, 2, "Saxon"));
    }

    private void addEvents(Deck deck){
        String type = "EVENT";
        int value = 1;

        for(int i = 0; i<2; i++) deck.AddCard(new CardEvent("King's Recognition", type, value, "King's_Recognition.png", game));
        for(int i = 0; i<2; i++) deck.AddCard(new CardEvent("Queen's Favor", type, value, "Queen's_Favor.png", game));
        for(int i = 0; i<2; i++) deck.AddCard(new CardEvent("Cour Called to Camelot", type, value, "Court_Called_to_Camelot.png", game));
        deck.AddCard(new CardEvent("Pox", type, value, "Pox.png", game));
        deck.AddCard(new CardEvent("Plauge", type, value, "Plague.png", game));
        deck.AddCard(new CardEvent("Chivalrous Deed", type, value, "Chivalrous_Deed.png", game));
        deck.AddCard(new CardEvent("Prosperity Throughout the Realm", type, value, "Prosperity_Throughout_the_Realm.png", game));
    }

    private void addTournaments(Deck deck){
        String type = "TOURNAMENT";
        int value = 1;
        String tat = "Tournament at ";
        
        deck.AddCard(new CardTournament(tat+"Camelot", type, value, header+"tournament1.png", game, 3));
        deck.AddCard(new CardTournament(tat+"Orkney", type, value, header+"tournament2.png", game, 2));
        deck.AddCard(new CardTournament(tat+"Tintagel", type, value, header+"tournament3.png", game, 1));
        deck.AddCard(new CardTournament(tat+"York", type, value, header+"tournament4.png", game, 0));
    }

    private void addWeapons(Deck deck){
        String type = "WEAPON";
        int value = 1;

        for(int i = 0; i<2; i++) deck.AddCard(new CardWeapon("Excalibur", type, value, header+"excalibur.png", game, 10));
        for(int i = 0; i<6; i++) deck.AddCard(new CardWeapon("Lance", type, value, header+"lance.png", game, 20));
        for(int i = 0; i<8; i++) deck.AddCard(new CardWeapon("Battle Ax", type, value, header+"battle_ax.png", game, 15));
        for(int i = 0; i<16; i++) deck.AddCard(new CardWeapon("Sword", type, value, header+"sword.png", game, 10));
        for(int i = 0; i<11; i++) deck.AddCard(new CardWeapon("Horse", type, value, header+"horse.png", game, 10));
        for(int i = 0; i<6; i++) deck.AddCard(new CardWeapon("Dagger", type, value, header+"dagger.png", game, 5));
    }

    private void addTests(Deck deck){
        String type = "TEST";
        int value = 1;

        for(int i = 0; i<2; i++){
            deck.AddCard(new CardTest("Test of Valor", type, value, header+"test3.png", game, 3));
            deck.AddCard(new CardTest("Test of Temptation", type, value, header+"test2.png", game, 3));
            deck.AddCard(new CardTest("Test of Morgan Le Fey", type, value, header+"test4.png", game, 3));
            deck.AddCard(new CardTest("Test of the Questing Beast", type, value, header+"test1.png", game, 3));
        }
    }

    private void addFoes(Deck deck){
        String type = "FOE";
        int value = 1;

        deck.AddCard(new CardFoe("Dragon", type, value, header+"Dragon.png", game, 50, 20));
        for(int i = 0; i<2; i++) deck.AddCard(new CardFoe("Giant", type, value, header+"Giant.png", game, 40, 0));
        for(int i = 0; i<4; i++) deck.AddCard(new CardFoe("Mordred", type, value, header+"Mordred.png", game, 30, 0));
        for(int i = 0; i<2; i++) deck.AddCard(new CardFoe("Green Knight", type, value, header+"Green_Knight.png", game, 25, 20));
        for(int i = 0; i<3; i++) deck.AddCard(new CardFoe("Black Knight", type, value, header+"Black_Knight.png", game, 25, 10));
        for(int i = 0; i<6; i++) deck.AddCard(new CardFoe("Evil Knight", type, value, header+"Evil_Knight.png", game, 20, 10));
        for(int i = 0; i<8; i++) deck.AddCard(new CardFoe("Saxon Knight", type, value, header+"Saxons_Knight.png", game, 15, 10));
        for(int i = 0; i<7; i++) deck.AddCard(new CardFoe("Robber Knight", type, value, header+"Robber_Knight.png", game, 15, 0));
        for(int i = 0; i<5; i++) deck.AddCard(new CardFoe("Saxons", type, value, header+"Saxons.png", game, 10, 10));
        for(int i = 0; i<4; i++) deck.AddCard(new CardFoe("Boar", type, value, header+"Boar.png", game, 5, 10));
        for(int i = 0; i<8; i++) deck.AddCard(new CardFoe("Thieves", type, value, header+"Thieves.png", game, 5, 0));
    }

    private void addAllies(Deck deck){
        String type = "ALLY";

        deck.AddCard(new CardAlly("Sir Galahad", type, 1, header+"ally10.png", game, 15));
        deck.AddCard(new CardAlly("Sir Lancelot", type, 1, header+"ally9.png", game, 15));
        deck.AddCard(new CardAlly("King Arthur", type, 2, header+"ally5.png", game, 10));
        deck.AddCard(new CardAlly("Sir Tristan", type, 1, header+"ally4.png", game, 10));
        deck.AddCard(new CardAlly("King Pellinore", type, 1, header+"ally2.png", game, 10));
        deck.AddCard(new CardAlly("Sir Gawain", type, 1, header+"ally1.png", game, 10));
        deck.AddCard(new CardAlly("Sir Percival", type, 1, header+"ally3.png", game, 5));
        deck.AddCard(new CardAlly("Queen Guinevere", type, 3, header+"ally6.png", game, 0));
        deck.AddCard(new CardAlly("Queen Iseult", type, 2, header+"ally8.png", game, 0));
        for(int i = 0; i<10; i++) deck.AddCard(new CardAlly("Merlin", type, 1, header+"ally7.png", game, 0));
    }

    private void addArmours(Deck deck){
        for(int i=0; i<8; i++) deck.AddCard(new CardArmour("armour", "ARMOUR", 1, header+"armour1.png", game, 10));
    }
}