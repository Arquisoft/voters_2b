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
	public void test() throws Exception
	{
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo("User Management Service"));
	}
	
	@Test
	public void voterEncontradoYcontraseñaCorrecta() throws Exception
	{
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String userURI = base.toString() + "/user";
		
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("jk@gmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"jk@gmail.com\",\"name\":\"Jack\",\"nif\":\"980151\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("josep@hotmail.com", "2"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"josep@hotmail.com\",\"name\":\"Jose\",\"nif\":\"730438\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("PaGu@terra.com", "3"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"PaGu@terra.com\",\"name\":\"Paula\",\"nif\":\"210953\",\"poolingState\":1}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("alf@hotmail.com", "4"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"alf@hotmail.com\",\"name\":\"Alfonso\",\"nif\":\"455846\",\"poolingState\":1}"));
		
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("GlezIr@hotmail.com", "1"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"GlezIr@hotmail.com\",\"name\":\"Irene\",\"nif\":\"565861\",\"poolingState\":2}"));
		
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("fcano@terra.com", "2"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"fcano@terra.com\",\"name\":\"Fernando\",\"nif\":\"564512\",\"poolingState\":3}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("vinu@hotmail.com", "3"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"vinu@hotmail.com\",\"name\":\"Vinuesa\",\"nif\":\"514685\",\"poolingState\":3}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("soto@hotmail.com", "4"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"soto@hotmail.com\",\"name\":\"Sotorrío\",\"nif\":\"314896\",\"poolingState\":3}"));
		
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("hg@gmail.com", "5"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"hg@gmail.com\",\"name\":\"Hugo\",\"nif\":\"214848\",\"poolingState\":4}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("diana23@hotmail.com", "6"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"diana23@hotmail.com\",\"name\":\"Diana\",\"nif\":\"197235\",\"poolingState\":4}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("javiG_6@gmail.com", "7"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"javiG_6@gmail.com\",\"name\":\"Javier\",\"nif\":\"156585\",\"poolingState\":4}"));
		
		response = template.postForEntity(userURI, new PeticionServicioWeb("luisValdés@terra.com", "8"), String.class);
		assertThat(response.getBody(), equalTo("{\"email\":\"luisValdés@terra.com\",\"name\":\"Luis\",\"nif\":\"126945\",\"poolingState\":4}"));
	}
		
		// ====================================================
		//  Se encuentra el voter, pero la contraseña está mal
		// ====================================================
		
		
		
		//== No funciona todavía ==
//		response = template.postForEntity(userURI, new PeticionServicioWeb("paco@gmail.com", "1"), String.class);
//		assertThat(response.getBody(), equalTo("{404 Not found}"));
	
	
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