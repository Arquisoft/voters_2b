package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import hello.model.Voter;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	 @Bean
		public CommandLineRunner init(VoterRepository repository) {
			return (args) -> { 
				// save a voter
				repository.save(new Voter("Jack", "980151","pepe@gmail.com",1,"1"));

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
=======
public class Application
{
	@Bean
	public CommandLineRunner init(VoterRepository repository)
	{
		return (args) ->
		{
			// save a voter
			repository.save(new Voter("Jack", "980151", "pepe@gmail.com", 1, "1"));
			
			/*
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			
			for (Customer customer : repository.findAll())
			{
>>>>>>> origin/master
				log.info(customer.toString());
			}
			
			log.info("");
			
			
			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");
			
			
			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			
			for (Customer bauer : repository.findByLastName("Bauer"))
			{
				log.info(bauer.toString());
			}
			
			log.info("");
			*/
		};
	}
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class,args);
    }
  
}