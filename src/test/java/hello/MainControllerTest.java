package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import javax.xml.ws.http.HTTPException;

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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class MainControllerTest
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
		
		
		// ==========================================
		
		String userURI = base.toString() + "/user";
		response = template.postForEntity(userURI, new PeticionServicioWeb("pepe@gmail.com", "1"), String.class);
		
		assertThat(response.getBody(), equalTo("{\"email\":\"pepe@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		
		//== No funciona todavía ==
//		response = template.postForEntity(userURI, new PeticionServicioWeb("paco@gmail.com", "1"), String.class);
//		assertThat(response.getBody(), equalTo(new HTTPException(404)));
		
		
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