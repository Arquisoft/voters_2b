package es.uniovi.asw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application
{
	@Bean
	public CommandLineRunner init(VoterRepository repository)
	{
		return (args) ->
		{ 
			// save a voter
			repository.save(new Voter("Jack", "980151", "jk@gmail.com", 1, "1"));
			repository.save(new Voter("Jose", "730438", "josep@hotmail.com", 1, "2"));
			repository.save(new Voter("Paula", "210953", "PaGu@terra.com", 1, "3"));
			repository.save(new Voter("Alfonso", "455846", "alf@hotmail.com", 1, "4"));
			
			repository.save(new Voter("Irene", "565861", "GlezIr@hotmail.com", 2, "1"));
			
			repository.save(new Voter("Fernando", "564512", "fcano@terra.com", 3, "2"));
			repository.save(new Voter("Vinuesa", "514685", "vinu@hotmail.com", 3, "3"));
			repository.save(new Voter("Sotorrío", "314896", "soto@hotmail.com", 3, "4"));
			
			repository.save(new Voter("Hugo", "214848", "hg@gmail.com", 4, "5"));
			repository.save(new Voter("Diana", "197235", "diana23@hotmail.com", 4, "6"));
			repository.save(new Voter("Javier", "156585", "javiG_6@gmail.com", 4, "7"));
			repository.save(new Voter("Luis", "126945", "luisValdés@terra.com", 4, "8"));
			
		};
	}
	
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}