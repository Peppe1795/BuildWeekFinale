package BuildWeek.Epic_Energy_Service.utente.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BusinessRunner implements CommandLineRunner {
	@Autowired
	private RandomInstanceGenerator rndGenerator;

	@Override
	public void run(String... args) throws Exception {
//		rndGenerator.randomUtenteGenerator(20);
//		rndGenerator.randomClienteGenerator(100);

	}

}
