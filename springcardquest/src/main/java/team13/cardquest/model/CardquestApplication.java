package team13.cardquest.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//adding for testing purposes

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import team13.cardquest.BlobQuest;
import team13.cardquest.Card;
import team13.cardquest.Game;
import team13.cardquest.Player;

import java.util.ArrayList;
import java.util.Arrays;

//end

//@SpringBootApplication (original code)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class CardquestApplication {
	private Game game=new Game();

	public static void main(String[] args) {
		SpringApplication.run(CardquestApplication.class, args);
		System.out.println("hello, world!");
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	@GetMapping("/hello")
	public ResponseEntity<Object> helloWorld(){
		String s = "hello World";
		return new ResponseEntity<>(new String[]{"hello world"}, HttpStatus.OK);
	}

	/*@PostMapping("/join")
	public ResponseEntity<Boolean> joinGame(@RequestBody String name){
		return new ResponseEntity<>(game.update(name,"join"), HttpStatus.OK);
	}

	@GetMapping("/start/{name}")
	public ResponseEntity<Boolean> startGame(@PathVariable("name") String name){
		return new ResponseEntity<>(game.update(name,"startgame"),HttpStatus.OK);
	}

	@GetMapping("/turn/{name}")
	public ResponseEntity<Boolean> turnStart(@PathVariable("name") String name){
		return new ResponseEntity<>(game.update(name,"turn start"),HttpStatus.OK);
	}*/
	//old mappings in case something breaks


	//new mappings as follows
	
	//request to register a player under the name given, returns the name they will use (which would change if there are duplicates)
	@PostMapping("/joinGame")
	public ResponseEntity<Object> joinGame(@RequestBody String name){
		return new ResponseEntity<>(new String[]{game.joinGame(name)}, HttpStatus.OK);
	}

	//request to start the game, player will then wait until all other players are ready to begin
	@GetMapping("/startGame/{name}")
	public ResponseEntity<Boolean> startGame(@PathVariable("name") String name){
		return new ResponseEntity<>(game.startGame(name), HttpStatus.OK);
	}

	//request for any update signals to the corresponding player
	@GetMapping("/getUpdates/{name}")
	public ResponseEntity<ArrayList<String>> getUpdates(@PathVariable("name") String name){

		return new ResponseEntity<>(game.getUpdates(name),HttpStatus.OK);
		//return new ResponseEntity<>(new ArrayList<String>(),HttpStatus.OK);
		//return new ResponseEntity<>(game.joinGame(name), HttpStatus.OK);
	}

	// request for player with specified name

	@GetMapping("/getPlayer/{name}")
	public ResponseEntity<Player> getPlayer(@PathVariable("name") String name){
		return new ResponseEntity<>(game.getPlayer(name),HttpStatus.OK);
	}

	//accept participation in quest
	@GetMapping("/acceptParticipation/{name}")
	public ResponseEntity<Void> acceptParticipation(@PathVariable("name") String name){
		game.questAcceptParticipation(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//reject participation in quest
	@GetMapping("/rejectParticipation/{name}")
	public ResponseEntity<Void> rejectParticipation(@PathVariable("name") String name){
		game.questRejectParticipation(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//get story Card
	@GetMapping("/getStoryCard")
	public ResponseEntity<Card> getStoryCard(){
		return new ResponseEntity<>(game.getStoryCard(),HttpStatus.OK);
	}

	//get active Quest
	@GetMapping("/getActiveQuest")
	public ResponseEntity<BlobQuest> getActiveQuest(){
		return new ResponseEntity<>(game.getActiveBlobQuest(),HttpStatus.OK);
	}

	//send playable hand to quest foe
	@PutMapping("/questFoeReceivePlayableHand/{name}")
	public ResponseEntity<Void> questFoeReceivePlayableHand(@PathVariable("name") String name, @RequestBody ArrayList<Card> hand ){
		game.questFoeReceivePlayableHand(name,hand);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// request for getting current player

	@GetMapping("/getCurrentPlayer")
	public ResponseEntity<Player> getCurrentPlayer(){
		return new ResponseEntity<>(game.getCurrentPlayer(),HttpStatus.OK);
	}



	// declining sponsorship
	@GetMapping("/declineSponsorship")
	public ResponseEntity<Void> declineSponsorship(){
		game.sponsorshipDeclined();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// accepting sponsorship
	@GetMapping("/acceptSponsorship")
	public ResponseEntity<Void> acceptSponsorship(){
		game.sponsorshipAccepted();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//receiving stages
	@PutMapping("/receiveStages")
	public ResponseEntity<Void> receiveStages(@RequestBody ArrayList<ArrayList<Card>> stages){
		game.receiveStages(stages);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//get al players
	@GetMapping ("/getAllPlayersStatus")
	public ResponseEntity<Object> getAllPlayersStatus(){
		return new ResponseEntity<>(game.getAllPlayersStatus(),HttpStatus.OK);
	}

	//play ally
	@PutMapping ("/playAlly/{name}")
	public ResponseEntity<Void> playAlly(@PathVariable("name") String name,@RequestBody Card c){
		game.playAlly(name,c);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//sending stage
	@PutMapping("/receiveStage")
	public ResponseEntity<Void> sendStage(@RequestBody ArrayList<Card> cards){
		game.receiveStage(cards);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//get Stage Preparation String
	@GetMapping("/getStagePreparationString")
	public ResponseEntity<Object> getStagePreparationString(){
		return new ResponseEntity<>(new String[]{game.getStagePreparationString()},HttpStatus.OK);

	}

	// rejecting stage setup
	@GetMapping("rejectStageSetup")
	public  ResponseEntity<Void> rejectStageSetup(){
		game.rejectStageSetup();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// get event log
	@GetMapping("/getEventLog")
	public ResponseEntity<ArrayList<String>> getEventLog(){
		return new ResponseEntity<>(game.getEventLog(), HttpStatus.OK);
	}

	// discard card
	@PutMapping("/discardCard/{name}")
	public  ResponseEntity<Void> discardCard(@PathVariable("name") String name, Card c){
		game.discardCard(name,c);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Get winner
	@GetMapping("/getWinner")
	public  ResponseEntity<Object> getWinner(){
		return new ResponseEntity<>(new String[]{game.getWinner()},HttpStatus.OK);
	}

	//get stage playing string

	@GetMapping("/getStagePlayingString")
	public ResponseEntity<Object> getStagePlayingString(){
		return new ResponseEntity<>(new String[]{game.getStagePlayingString()},HttpStatus.OK);
	}

	//display the event
	@GetMapping("/getEvent")
	public ResponseEntity<Object> getEvent(){
		return new ResponseEntity<>(new String[]{game.displayEvent()},HttpStatus.OK);
	}

	//accept event
	@PutMapping ("/acceptEvent")
	public ResponseEntity<Void> acceptEvent(@RequestBody String name){
		game.acceptEvent(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
