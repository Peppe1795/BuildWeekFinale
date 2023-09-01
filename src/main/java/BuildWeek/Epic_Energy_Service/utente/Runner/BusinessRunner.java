package BuildWeek.Epic_Energy_Service.utente.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class BusinessRunner implements CommandLineRunner {
	@Autowired
	private RandomInstanceGenerator rndGenerator;

	@Override
	public void run(String... args) throws Exception {

		Thread popolateDb = new Thread(() -> {
			try {
				rndGenerator.randomUtenteGenerator(20);
				rndGenerator.randomClienteGenerator(100);
			} finally {
				Thread.currentThread().interrupt();
			}

		});

		popolateDb.start();
		popolateDb.join();

		rndGenerator.randomFattureGenerator(1000);

	}

}
