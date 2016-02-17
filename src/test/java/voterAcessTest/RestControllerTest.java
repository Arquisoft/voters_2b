package voterAcessTest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import voterAcess.Application;
import webService.Error404Response;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class RestControllerTest
{
	@Value("${local.server.port}")
	private int port;
	
	private URL base;
	private RestTemplate template;
	
	
	@Before
	public void setUp() throws Exception
	{
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}
	
	
	@Test
	public void getLanding() throws Exception
	{
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo("User Management Service"));
		
		
		// =======================
		//  Se encuentra el voter
		// =======================
		
		String userURI = base.toString() + "/user";
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		
		// ====================================================
		//  Se encuentra el voter, pero la contraseña está mal
		// ====================================================
		
		
		
		//== No funciona todavía ==
//		response = template.postForEntity(userURI, new PeticionServicioWeb("paco@gmail.com", "1"), String.class);
//		assertThat(response.getBody(), equalTo("{404 Not found}"));
		
		
	}
	
	public class PeticionServicioWeb
	{
		private String email;
		private String password;
		
		
		public PeticionServicioWeb() {}
		
		public PeticionServicioWeb(String email, String password)
		{
			this.email = email;
			this.password = password;
		}
		
		
		public String getEmail()
		{
			return email;
		}
		
		public void setEmail(String email)
		{
			this.email = email;
		}
		
		public String getPassword()
		{
			return password;
		}
		
		public void setPassword(String password)
		{
			this.password = password;
		}
	}
}