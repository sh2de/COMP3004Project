package team13.cardquest.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//adding for testing purposes

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import team13.cardquest.Game;
import team13.cardquest.Player;


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

	@PostMapping("/join")
	public ResponseEntity<Object> joinGame(@RequestBody String name){
		Player newPlayer=new Player(name);
		game.addPlayer(newPlayer);
		return new ResponseEntity<>(newPlayer, HttpStatus.OK);
	}

	
}
