package team13.cardquest;

import java.util.ArrayList;

//The game object represents the internal state of a game session
//It is responsible for keeping track of all the game elements, but does not automatically advance the game itself
public class Game {
    DeckFactory df = new DeckFactory(this);
    Deck storydeck = df.CreateDeck(true);
    Deck adventuredeck = df.CreateDeck(false);
    int numPlayers = 0;
    int currentTurn = 1;
    boolean firstTurn = false;//lazy fix
    int sponsor = -1; //variable used for iterating through available sponsors
    Player currentSponsor = null;
    ArrayList<Player> players = new ArrayList<>();

    
    Card currentStory = null;

    static BlobQuest activeQuest = null;
    int activeStage = 0;
    ArrayList<ArrayList<Card>> questStages = new ArrayList<ArrayList<Card>>();
    int currentSetupStage = 1;
    int setupPreviousPower = 0;
    String rejectionReason = "";

    int questBonus = 0;
    String questStageResults = "";
    String questFinalResults = "";
    
    String eventText = "";
    
    static BlobFoe currentFoe = null;
    static BlobWeapon currentWeapon = null;
    static BlobAlly currentAlly = null;

    ArrayList<String> eventLog = new ArrayList<>();

    String winnerName = "";

    //debug line
    //debug end

    /*the state variable is what will be used to check what moves are currently legal or not.
    depending on the state, the game may end up waiting to receive input from all players. (possibly have a timer to prevent getting stuck?)
    an index of the states will be described here below:
    "initialize"    wait for players to arrive. upon confirmation that all players have joined, start the game
    "turn start"    draw a card from the story deck upon receiving "draw" command from the current player
    "quest sponsor"   decide a sponsor for the quest. if the person who drew the card refuses to sponsor or cannot with their current cards, 
                    pass the choice to the next player. if all players refuse, discard the quest card.
    "quest setup"   the sponsor now decides which cards to play for the quest
    "quest foe"     players must select the cards they wish to use before the foe is revealed and result is calculated    
    "quest test"    tbd
    */

    public ArrayList<String> getEventLog(){
        //eventLog.add("test string!");
        ArrayList<String> e = new ArrayList<>();
        for (int i = eventLog.size()-1; i >= 0; i--){
            e.add(eventLog.get(i));
        }
        return e;
    }

    public void addEventString(String s){
        eventLog.add(s);
        if (eventLog.size() > 10){
            eventLog.remove(0);
        }

        for (Player p : players) {
            p.addEventSignal("EVENT_LOG_UPDATE");
        }
    }

    //helper function to check if no players are waiting
    public boolean allPlayersReady(){
        boolean flag = true;
        for (Player player : players) {
            if (player.getWaiting()){flag = false;}
        }
        return flag;
    }
    
    //helper function to make all players ready
    public void forceAllReady(){
        for (Player player : players) {
            player.setWaiting(false);
        }
    }

    //helper function to make all players not ready
    public void forceAllUnready(){
        for (Player player : players) {
            player.setWaiting(true);
        }
    }

    public Player getPlayer(String name){
        for (Player player : players) {
            if(player.getName().equals(name)){
                return player;
            }
        }
        return new Player("");
    }

    public Game(){
        
    }

    //GAME SETUP FUNCTIONS---------------------------------------------------------------------------------------------------------------------
    //api call to add new player to the game
    public String joinGame(String name){
        if (numPlayers > 3){
            return "";
        }

        for (Player player : players) { //edit duplicate names
            if (player.getName().equals(name)){name += (numPlayers+1);}
        }

        //missing functionality: make sure there are no duplicate player names
        addPlayer(new Player(name));
        System.out.println("player "+name+" has joined the game"); //debug message
        return name;
    }

    public void addPlayer(Player p){
        players.add(p);
        numPlayers += 1;
    }

    //api call to set player as ready to start the game
    public boolean startGame(String name){
        //set player as ready to start, if all players are ready give them all the “ALL_PLAYERS_READY” signal
        getPlayer(name).setWaiting(false);
        if (allPlayersReady()){
            for (Player player : players) {
                player.addEventSignal("ALL_PLAYERS_READY");
                for(int i = 0; i < 12; i++){
                    player.addCardToHand(adventuredeck.draw());
                }
            }
            
            turnStart();
        }
        return true;
    }


    //TURN PROCESSING FUNCTIONS----------------------------------------------------------------------

    //helper function to pass to the next turn no matter the number of players
    public void nextTurn(){
        currentTurn += 1;
        if (currentTurn > numPlayers){currentTurn = 1;}
    }

    //function to be called at the start of a turn. draws a card for the current player and sends the proper signals
    public void turnStart(){
        nextTurn();
        //if (firstTurn){currentTurn--;firstTurn=false;}//lazy fix
        addEventString("Player "+players.get(currentTurn-1).getName()+"'s turn:");
        addEventString("Now drawing a card from the story deck.");
        currentStory = storydeck.draw();
        storydeck.discard(currentStory);
        addEventString(currentStory.getName()+" was drawn!");
        if (currentStory.getType().equals("QUEST")){
            //quest card drawn
            currentStory.play();
            for (Player player : players) {
                player.addEventSignal("DRAW_STORY");
            }

            
            sponsor = 0;
            currentStory.play();
            forceAllUnready();
            getSponsor();

        } else if (currentStory.getType().equals("EVENT")){
            //event card drawn
            forceAllUnready();

            executeEvent();

            for (Player p : players) {
                p.addEventSignal("DISPLAY_EVENT");
            }

        } else if (currentStory.getType().equals("TOURNAMENT")){
            //tournament card drawn

        } else {
            //invalid card
            currentStory = null;
            turnStart();
        }
        //activeQuest = new Quest();
        //c.initQuest(activeQuest);
        //state = "quest sponsor";
        //sponsor = 0;
        //forceAllUnready();
    }

    //QUEST PROCESSING FUNCTIONS----------------------------------------------------------------------
    
    /*quest behavior as follows
    quest card is drawn
    for each player, check if they can sponsor it. it yes, send signal to ask if they wish to do so. if not, pass option to next player who can sponsor it
    if no players can sponsor it, end turn
    if a player decides to sponsor it, request the cards they wish to sponsor it with
    once received, proceed with quest. send signal to each player who is still alive to pick cards for battle
    receive cards for each player. once all have been received, show the results, repeat until all phases are done or all players fail
    */

    public BlobQuest getActiveBlobQuest(){return activeQuest;}

    public Card getStoryCard(){return currentStory;}

    public static void ReceiveQuest(BlobQuest q){
        activeQuest = q;
    }
    
    public void getSponsor(){ //function to be called to iterate through possible sponsors for a quest
        if (allPlayersReady()){ //if all players rejected the sponsor, discard the quest and begin a new turn
            addEventString("No one wanted to sponsor the quest...");
            activeQuest = null;
            turnStart();
            return;
        }

        Player p = players.get((currentTurn - 1 + sponsor)%numPlayers);
        sponsor++;

        if (p.canSponsor(activeQuest)){
            p.addEventSignal("REQUEST_SPONSORSHIP"); //signal to ask if the player would like to sponsor the following quest
        } else {
            p.setWaiting(false);
            getSponsor();
        }
        return;
    }

    public void sponsorshipDeclined(){//function that runs when a player rejects a quest
        addEventString(players.get((currentTurn - 2 + sponsor)%numPlayers).getName()+" has declined to sponsor the quest!");
        players.get((currentTurn - 2 + sponsor)%numPlayers).setWaiting(false);
        getSponsor();
    }

    public void sponsorshipAccepted(){//function that runs when a player accepts a quest to sponsor to signal other players
        currentSponsor = players.get((currentTurn - 2 + sponsor)%numPlayers);
        addEventString(currentSponsor.getName()+" has accepted the quest as a sponsor!");
        currentSponsor.saveBackupHand();
        currentSetupStage = 0;
        setupPreviousPower = 0;
        activeStage = 0;
        adventuredeck.discardList(currentSponsor.discardTempDiscards());
        rejectionReason = "";
        forceAllUnready(); //we need a response from all players
        for (Player player : players) {
            if ((player).equals(currentSponsor)){
                player.addEventSignal("CREATE_QUEST"); //signal to select cards for the quest
            } else {
                player.addEventSignal("WAIT_FOR_QUEST_CREATION"); //signal to know that a quest is about to begin and request participation
            }
        }
    }

    public void receiveStage(ArrayList<Card> tempstage){
        ArrayList<Card> stage = new ArrayList<Card>();
        for (Card card : tempstage) {
            stage.add(currentSponsor.getCardByName(card.getName()));
        }

        questStages.add(stage);
        currentSetupStage ++;
        //if invalid, reset player state and restart
        if (!receiveStages(questStages)){
            rejectStageSetup();
            return;
        }
        //if valid, check if all stages are done. if so filter them by power and begin
        if (currentSetupStage == activeQuest.stages){
            activeStage = 1;
            currentSponsor.setWaiting(false);
            questAttemptStart(); 
        } else { //if not done, subtract from the player's hand the cards used and send signal to input more stages
            for (Card card : stage) {
                currentSponsor.discardCardByName(card.getName());
            }
            currentSponsor.addEventSignal("CREATE_QUEST");
        }
    }

    public void sortStages(){
        ArrayList<ArrayList<Card>> sortedStages = new ArrayList<>();
        boolean flag = true;
        int index = questStages.size();
        while(flag){
            int smallestPower = 99999;
            for (ArrayList<Card> stage : questStages) {
                if (getPower(stage) < smallestPower){
                    smallestPower = getPower(stage);
                }
            }
            for (ArrayList<Card> stage : questStages) {
                if (getPower(stage) == smallestPower){
                    sortedStages.add(stage);
                    index--;
                }
            }
            if (index==0){flag = false;}
        }
        
        questStages = sortedStages;
    }

    public String getStagePreparationString(){
        if (activeStage == 0){return "Waiting...";}
        return "Stage "+(currentSetupStage+1)+"/"+activeQuest.stages + rejectionReason;
    }

    public String getStagePlayingString(){
        return "Stage "+(activeStage)+"/"+activeQuest.stages;
    }

    public boolean receiveStages(ArrayList<ArrayList<Card>> stages){ //return true if setup is valid, false otherwise
        System.out.println(stages);
        //start by checking if the received list of cards is valid
        //FIRST CHECK: DOES THE PLAYER HAVE THE REQUIRED CARDS? FOR SIMPLICITY ASSUME YES
        boolean testFlag = false;//boolean value to see if test has been played yet. only one test may be played per quest
        //SECOND CHECK: IS THERE ONLY ONE TEST?
        /*if (stages.size() != activeQuest.stages){
            //rejectStageSetup();
            rejectionReason = "too many stages!";
            return false;
        }*/
        setupPreviousPower = 0;
        for (ArrayList<Card> stage : stages) {
            boolean foeFlag = false;
            ArrayList<Card> weapons = new ArrayList<>();//this list is used to check for duplicate weapons 
            for (Card card : stage) {
                //System.out.println(card);//debug line
                //System.out.println(card.toString());
                switch(card.getType()){
                    case "FOE":
                        if (foeFlag){
                            rejectionReason = "(you cannot play more than one foe)";
                            //rejectStageSetup();
                            return false;
                        }
                        foeFlag = true;

                        break;
                    case "WEAPON":
                        weapons.add(card);
                        break;
                    case "TEST":
                        if (testFlag || stage.size() > 1){
                            //rejectStageSetup();
                            rejectionReason = "(only one test may be played in a quest)";
                            return false;
                        }
                        testFlag = true;
                        break;
                    default: //reject if an invalid card is received
                        //rejectStageSetup();
                        rejectionReason = "(invalid card type submitted)";
                        return false;
                }
            }
            //reject if no foe or test is present
            if ((!foeFlag && !testFlag)||(foeFlag && testFlag)){
                //rejectStageSetup();
                rejectionReason = "(each stage must have either one foe or one test)";
                return false;
            }

            //reject if duplicate weapons
            while (weapons.size() != 0){
                Card w = weapons.remove(0);
                for (Card weapon : weapons) {
                    if (w.getName().equals(weapon.getName())){
                        //reject if they share a name
                        //rejectStageSetup();
                        rejectionReason = "(duplicate weapons are not allowed)";
                        return false;
                    }
                }
            }
            
            //power scaling rejection test
            int testingPower = getPower(stage);
            if (testingPower != -1){//if not a test
                if (testingPower < setupPreviousPower){
                    rejectionReason = "(each stage must have a higher total power than the last!)";
                    return false;
                }
                setupPreviousPower = testingPower;
            }
        }



        return true;
        //old functionality down there, this is just a quest validator now

        //if the quest is accepted, automatically sort them by power
        //TBD
        //just test the signal for now
        //questStages = stages;
        //activeStage = 1;
        //currentSponsor.setWaiting(false);
        //questAttemptStart();        
    }

    public void rejectStageSetup(){ //restart quest creation from the beginning
        questStages = new ArrayList<>();
        currentSetupStage = 0;
        setupPreviousPower = 0;
        currentSponsor.loadBackupHand();
        System.out.println("stage rejected for reason "+rejectionReason);
        currentSponsor.addEventSignal("CREATE_QUEST");
    }

    public void questAcceptParticipation(String name){
        Player p = getPlayer(name);
        p.setWaiting(false);
        p.setAlive(true);
        questAttemptStart();
    }

    public void questRejectParticipation(String name){
        Player p = getPlayer(name);
        p.setWaiting(false);
        questAttemptStart();
    }

    public void questAttemptStart(){//try to start the quest if possible
        
        boolean anyWaiting = false;

        for (Player p : players) {
            if (p.getWaiting()){anyWaiting = true;}
        }
        if (!anyWaiting){
            //sortStages(); rather than sort stages, maybe i could implement a rejection for it
            for (Player p : players) {
                p.addEventSignal("QUEST_START");
            }
            questTurn();
        }
        
    }

    public void questTurn(){//handle the start of a quest's stage
        
        //first step: check if stages are left in the quest
        if (activeStage > activeQuest.stages){
            questAnnounceResults();
            return; 
        }

        //second step: check if any players are alive
        boolean aliveFlag = false;
        for (Player player : players) {
            if (player.getAlive()){aliveFlag = true;}
        }
        if (!aliveFlag){
            questAnnounceResults();
            return;
        }

        //third step: announce foe or test

        //fourth step: get cards to be played from alive players
        for (Player player : players) {
            if (!(player).equals(currentSponsor) && player.getAlive()){
                player.addCardToHand(adventuredeck.draw());
                player.setWaiting(true);
                player.addEventSignal("QUEST_FOE_SELECT_CARDS"); //signal to select cards for the foe
            } 
        }
    }

    public void questFoeReceivePlayableHand(String name, ArrayList<Card> temphand){//receive the cards played by the player for a quest
        //step 1: check if cards are valid. on fail, resend signal
        boolean invalidflag = false;
        //INCOMPLETE: ASSUMING VALIDITY FOR NOW
        ArrayList<Card> hand = new ArrayList<>();
        for (Card card : temphand) {
            hand.add(getPlayer(name).getCardByName(card.getName()));
        }

        if (invalidflag){
            getPlayer(name).addEventSignal("QUEST_FOE_SELECT_CARDS");
            return;
        }

        //step 2: set player as ready, if all players ready move on to the next stage
        getPlayer(name).setPlayableHand(hand);
        getPlayer(name).setWaiting(false);
        //step 3: check if all other players are ready
        boolean anyWaiting = false;

        for (Player p : players) {
            if (p.getWaiting()){anyWaiting = true;}
        }
        if (!anyWaiting){
            System.out.println("all hands received, now processing result");
            questFoeStageResults();
        }

    }

    public void questFoeStageResults(){
        questStageResults = "";
        int foePower = getPower(questStages.get(activeStage - 1));
        addEventString("The approaching foe has a power of "+foePower+"!");
        for (Player player : players) {
            if (player.getAlive()){
                //if player is alive, check if they survived
                if (foePower > player.getPower() + getPower(player.getPlayableHand())){//if foe was stronger, kill the player
                    player.setAlive(false);
                    //questStageResults += player.getName() + " was felled in battle!\n";
                    addEventString(player.getName() + " was felled in battle!");
                    player.addEventSignal("PLAYER_QUEST_DEAD");
                } else{
                    //questStageResults += player.getName() + " triumphed in battle!\n";
                    addEventString(player.getName() + " triumphed in battle!");
                }                
            }
        }
        activeStage++;
        System.out.println("result processed, now sending signal");
        System.out.println(questStageResults);
        for (Player player : players){player.addEventSignal("QUEST_FOE_SHOW_RESULTS");}
        questTurn();
    }

    public void questAnnounceResults(){
        for (Player player : players) {
            if (player.getAlive()){
                player.editShields(activeQuest.stages + questBonus);
                addEventString(player.getName() + " gets " + activeQuest.stages + " shields for surviving the quest!");
                player.setAlive(false);
                //questFinalResults += player.getName() + " gets " + activeQuest.stages + " shields for surviving the quest!\n";
            }else if (player.equals(currentSponsor)){
                player.addCardToHand(adventuredeck.draw());//draw 1 per stage + cards played in a stage
                for (ArrayList<Card> stage : questStages) {
                    for (int i = 0; i < stage.size(); i++){
                        player.addCardToHand(adventuredeck.draw());
                    }
                }
            }
            player.addEventSignal("QUEST_OVER");
        }
        //MISSING: CHECK FOR A WINNER

        for (Player p : players) {
            if (p.getRank().equals("knight of the round table")){
                winnerName = p.getName();
                declareWin();
                return;
            }
        }

        questBonus = 0; //reset quest bonus
        turnStart();
    }


    //this function is what each quest will call so that the game can put itself in the right state for the quest
    //public void ReceiveQuest(BlobQuest q){System.out.println(q.name + " " + q.stages + " " + q.namedFoe);}
    //public static void ReceiveAlly(BlobAlly a){System.out.println(a.name + " " + a.power + " " + a.value);} //add ally to the allies of player who played it
    //public void ReceiveFoe(BlobFoe f){System.out.println(f.name + " " + f.power + " " + f.boost);}
    //public void ReceiveWeapon(BlobWeapon w){System.out.println(w.name + " " + w.power);}

    public static void ReceiveFoe(BlobFoe f){currentFoe = f;}
    public static void ReceiveWeapon(BlobWeapon w){currentWeapon = w;}
    public static void ReceiveAlly(BlobAlly a){currentAlly = a;}
    public static void ReceiveEvent(BlobEvent e){}
    public static void ReceiveArmour(BlobArmour a){}
    public static void ReceiveTournament(BlobTournament t){}
    public static void ReceiveTest(BlobTest t){}

    public boolean defeatedFoe(Player p, ArrayList<Card> playerCards, ArrayList<Card> questCards){ //check if the player successfully defeated a foe or not
        if (p.getPower() + getPower(playerCards) >= getPower(questCards)){return true;}
        return false;
    }

    public int getPower(ArrayList<Card> hand){//get power sum of the current combination of cards
        int p = 0;
        for (Card card : hand) {
        //for (Card cardData : hand) {
            //Card card = player.getCardByName(cardData.getName());
            card.play();
            switch(card.getType()){
                case "FOE":
                    p += currentFoe.power;
                    //add power if foe is named in the quest
                    if (activeQuest.namedFoe.equals("all")){//all case
                        p+=currentFoe.boost;
                    }else if(activeQuest.namedFoe.equals("saxon")&&(currentFoe.name.equals("saxons")||currentFoe.name.equals("saxon knight"))){//saxon case
                        p+=currentFoe.boost;
                    }else if(activeQuest.namedFoe.equals(currentFoe.name)){//regular named case
                        p+=currentFoe.boost;
                    }
                    //bosspower end
                    currentFoe = null;
                    break;
                case "WEAPON":
                    p += currentWeapon.power;
                    currentWeapon = null;
                    break;
                default:
                    return -1;
                    
            }
            //p += card.GetPower();
        }
        return p;
    }



    //EVENT PROCESSING FUNCTIONS--------------------------------------------------------------------

    public String displayEvent(){
        return eventText;
    }

    public void acceptEvent(String name){
        getPlayer(name).setWaiting(false);
        if(allPlayersReady()){
            eventText = "";
            turnStart();
        }
    }

    public void executeEvent(){
        //String eventName = currentStory.getName();
        switch(currentStory.getName()){
            case "Chivalrous Deed":
                eventText = eventChivalrousDeed();
                break;
            case "Court Called to Camelot":
                eventText = eventCourtCalledToCamelot();
                break;
            case "King's Recognition":
                eventText = eventKingsRecognition();
                break;
            case "Plague":
                eventText = eventPlague();
                break;
            case "Pox":
                eventText = eventPox();
                break;
            case "Prosperity Throughout the Realm":
                eventText = eventProsperityThroughTheRealm();
                break;
            case "Queen's Favor":
                eventText = eventQueensFavor();
                break;
            case "King's Call To Arms":
                eventText = eventKingsCallToArms();
                break;
            default:
                eventText = "invalid event card";
                break;
        }

    }


    public String eventChivalrousDeed(){ //players with the lowest rank/amount of shields receives 3 shields
        int lowestShields = 10;
        for (Player player : players) {
            if (player.getShields()<lowestShields){
                lowestShields = player.getShields();
            }
        }

        for (Player player : players) {
            if (player.getShields()==lowestShields){
                player.editShields(3);
            }
        }
        return "A boon for the weak! A gift of 3 shields to our weakest knights!";
    }

    public String eventPox(){ //all players except the one who drew this card lose 1 shield
        for (Player player : players) {
            if (!player.equals(getCurrentPlayer())){
                player.editShields(-1);
            }
        }
        return "A pox has stricken all knights but " + players.get(currentTurn-1).getName() + "! They all lost one shields during their rest...";
    }

    public String eventPlague(){ //drawer loses two shields if possible
        getCurrentPlayer().editShields(-2);
        return "A plague has struck " + players.get(currentTurn-1).getName() + "! They lost two shields during their rest...";
    }

    public String eventKingsRecognition(){ //next quest completion gets 2 extra shields
        questBonus += 2;
        return "The king recognizes your efforts, and has prepared a special prize of two shields for your next quest...";
    }

    public String eventQueensFavor(){ //lowest ranked players draw 1 card
        int lowestShields = 10;
        String targetRank = "squire";
        for (Player player : players) {
            if (player.getShields()<lowestShields){
                lowestShields = player.getShields();
            }
        }

        for (Player player : players) {
            if (player.getShields()==lowestShields){
                targetRank = player.getRank();
            }
        }

        for (Player player : players) {
            if (player.getRank()==targetRank){
                player.addCardToHand(adventuredeck.draw());
            }
        }
        return "The queen has decided to favor the weak with a gift of one card!";
    }

    public String eventCourtCalledToCamelot(){//discard all allies
        for (Player player : players) {
            player.discardAllAllies();
        }
        return "The king summons all of thy allies! You must work alone from now on...";
    }

    public String eventKingsCallToArms(){//highest ranked players must discard 1 weapon, if impossible must discard 2 foes (INCOMPLETE)
        int highestShields = 0;
        String targetRank = "squire";
        for (Player player : players) {
            if (player.getShields()>highestShields){
                highestShields = player.getShields();
            }
        }

        for (Player player : players) {
            if (player.getShields()==highestShields){
                targetRank = player.getRank();
            }
        }

        //DISCARD PROCESS TBD
        //PROBABLY GONNA INVOLVE A SIGNAL
        return "This event is incomplete very sorry about that";
    }

    public String eventProsperityThroughTheRealm(){ //all players draw 2 cards
        for (Player player : players) {
            player.addCardToHand(adventuredeck.draw());
            player.addCardToHand(adventuredeck.draw());
        }
        return "Prosperity for all! A gift of 2 cards to all my knights!";
    }

    //TOURNAMENT PROCESSING FUNCTIONS----------------------------------------------------------------


    //WIN STATE FUNCTION

    public void declareWin(){
        for (Player p : players) {
            p.addEventSignal("DECLARE_WINNER");
        }
    }

    public String getWinner(){
        return winnerName;
    }

    //GENERAL USE FUNCTIONS---------------------------------------------------------------------------

    //api call to play an ally card
    public void playAlly(String name, Card c){
        Player p = getPlayer(name);
        Card card = p.getCardByName(c.getName());

        if (card.getType().equals("ALLY")){
            card.play();
            p.addAlly(currentAlly);
            p.discardCardByName(name);
        }
        
    }

    public ArrayList<String> getAllPlayersStatus(){
        if (players.size() < 2){return new ArrayList<String>();} //method needs more than 1 player
        ArrayList<String> status = new ArrayList<>();
        for (Player player : players) {
            String s = "";
            s += player.getName() + ": " + player.getHand().size() + " cards | " + player.getShields() + " shields";
            if (player.getAlive()){s += " (participating in story) ";}
            if (player.getWaiting()){s += " (waiting for input)";}
            status.add(s);
        }
        status.add("It is currently " + players.get(currentTurn-1).getName()+"'s turn");
        return status;
    }

    //api call to get a player's list of update signals
    public ArrayList<String> getUpdates(String name){
        return getPlayer(name).sendEventQueue();
    }

    //api call to get a current player

    public Player getCurrentPlayer(){
        return players.get(currentTurn-1);
    }
    
    //api call to discard a card
    public void discardCard(String name, Card c){
        Player p = getPlayer(name);
        p.discardCardByName(c.getName());
        p.discardTempDiscards();
    }

    //print function for debugging purposes
    public void print(){
        
        System.out.println("It is player "+currentTurn+"'s turn.");
        int pnum = 1;
        for (Player player : players) {
            System.out.println("Showing player "+(pnum++)+"'s information:");
            player.print();
        }
    }
}