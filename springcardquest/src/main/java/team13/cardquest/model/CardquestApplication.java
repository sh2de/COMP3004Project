package team13.cardquest.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//adding for testing purposes

//end

//@SpringBootApplication (original code)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CardquestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardquestApplication.class, args);
		System.out.println("hello, world!");
	}

}
