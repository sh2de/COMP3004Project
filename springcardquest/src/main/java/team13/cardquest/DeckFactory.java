package team13.cardquest

import org.json.simple.parser.*;

public class DeckFactory{
	public static Deck createDeck(boolean isStory){
		deck = new Deck();
		String url = "http://localhost:8080/images/testcard.png";

		JSONParser parser = new JSONParser();


		if(isStory){
			JSONArray allyArray = (JSONArray) parser.parse(new FileReader(".\\Cards\\Adventure\\Allies.json"));
			JSONArray armourArray = (JSONArray) parser.parse(new FileReader(".\\Cards\\Adventure\\Armours.json"));
			JSONArray foeArray = (JSONArray) parser.parse(new FileReader(".\\Cards\\Adventure\\Foes.json"));
			JSONArray weaponArray = (JSONArray) parser.parse(new FileReader(".\\Cards\\Adventure\\Weapons.json"));

			allyArray.foreach(ally -> {
				for(int i = 0; i<ally.frequency; i++){
					deck.stack.add(new AllyCard(ally.name, ally.bidWorth, url, ally.trigger));
				}
			});

		}else{
			JSONArray evenArray = (JSONArray) parser.parse(new FileReader(".\\Cards\\Story\\Events.json"));
			JSONArray QuestArray = (JSONArray) parser.parse(new FileReader(".\\Cards\\Story\\Quests.json"));

			questArray.foreach(quest -> {
				for(int i = 0; i<quest.frequency; i++){
					deck.stack.add(new QuestCard(quest.name, quest.stages, quest.foeName, quest.bidWorth, url));
				}
			});
		}

		return deck;
	}
}