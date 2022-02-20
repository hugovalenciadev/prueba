package co.com.ingeneo.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.com.ingeneo.prueba.entities.Client;
import co.com.ingeneo.prueba.repositories.ClientRepository;

@SpringBootApplication
@EnableJpaAuditing
public class PruebaApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client();
		client.setName("Hugo");	
		//client.setName("Hugo");
		
	}

}
