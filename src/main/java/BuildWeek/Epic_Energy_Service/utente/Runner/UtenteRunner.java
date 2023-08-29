package BuildWeek.Epic_Energy_Service.utente.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import BuildWeek.Epic_Energy_Service.utente.UtenteService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UtenteRunner implements CommandLineRunner {
	@Autowired
	UtenteService utenteService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 5; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String username = faker.name().username();
			String email = faker.internet().emailAddress();
			String password = faker.lorem().characters(6, 12);
			UtenteRequestPayload utente = new UtenteRequestPayload(name, surname, username, email, password);
			// utenteService.creaUtente(utente);

		}
	}
}
