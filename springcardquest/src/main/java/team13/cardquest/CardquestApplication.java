package team13.cardquest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
//adding for testing purposes

import org.springframework.web.bind.annotation.RestController;

//end

//@SpringBootApplication (original code)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class CardquestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardquestApplication.class, args);
	}

	
	@GetMapping
	public String hello() {
    	return "Hello World";
	}
	
}
