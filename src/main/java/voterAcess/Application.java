package voterAcess;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import voterAcess.model.Voter;
import voterAcess.persistence.VoterRepository;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	 @Bean
		public CommandLineRunner init(VoterRepository repository) {
			return (args) -> { 
				// save a voter
				repository.save(new Voter("Jack", "980151", "jk@gmail.com", 1, "1"));
				repository.save(new Voter("Jose", "730438", "josep@hotmail.com", 1, "2"));
				repository.save(new Voter("Paula", "730438", "PaGu@terra.com", 1, "3"));
				repository.save(new Voter("Alfonso", "730438", "alf@hotmail.com", 1, "4"));
				
				repository.save(new Voter("Irene", "730438", "GlezIr@hotmail.com", 2, "1"));
				
				repository.save(new Voter("Fernando", "730438", "fcano@terra.com", 3, "2"));
				repository.save(new Voter("Vinuesa", "730438", "vinu@hotmail.com", 3, "3"));
				repository.save(new Voter("Sotorrío", "730438", "soto@hotmail.com", 3, "4"));
				
				repository.save(new Voter("Hugo", "730438", "hg@gmail.com", 4, "5"));
				repository.save(new Voter("Diana", "730438", "diana23@hotmail.com", 4, "6"));
				repository.save(new Voter("Javier", "730438", "javiG_6@gmail.com", 4, "7"));
				repository.save(new Voter("Luis", "730438", "luisValdés@terra.com", 4, "8"));
				
				/*
				// fetch all customers
				log.info("Customers found with findAll():");
				log.info("-------------------------------");
				for (Customer customer : repository.findAll()) {
					log.info(customer.toString());
				}
	            log.info("");

				// fetch an individual customer by ID
				Customer customer = repository.findOne(1L);
				log.info("Customer found with findOne(1L):");
				log.info("--------------------------------");
				 */
		};
	}
	
    public static void main(String[] args)
    {
    	SpringApplication.run(Application.class,args);
    }
  
}