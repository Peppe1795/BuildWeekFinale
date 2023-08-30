package BuildWeek.Epic_Energy_Service.utente.Runner;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import BuildWeek.Epic_Energy_Service.cliente.ClienteRequestPayload;
import BuildWeek.Epic_Energy_Service.cliente.ClienteService;
import BuildWeek.Epic_Energy_Service.cliente.Tipo_cliente;
import BuildWeek.Epic_Energy_Service.comuni.Comune;
import BuildWeek.Epic_Energy_Service.comuni.ComuneService;
import BuildWeek.Epic_Energy_Service.fattura.Fattura;
import BuildWeek.Epic_Energy_Service.fattura.FatturaRequestPayload;
import BuildWeek.Epic_Energy_Service.fattura.FatturaService;
import BuildWeek.Epic_Energy_Service.fattura.Stato_fattura;
import BuildWeek.Epic_Energy_Service.indirizzo.Indirizzo;
import BuildWeek.Epic_Energy_Service.indirizzo.IndirizzoRequestPayload;
import BuildWeek.Epic_Energy_Service.indirizzo.IndirizzoService;
import BuildWeek.Epic_Energy_Service.utente.Ruolo;
import BuildWeek.Epic_Energy_Service.utente.Utente;
import BuildWeek.Epic_Energy_Service.utente.UtenteService;
import BuildWeek.Epic_Energy_Service.utente.payload.UtenteRequestPayload;

@Component
public class RandomInstanceGenerator {
	private final ClienteService cs;
	private final IndirizzoService is;
	private final ComuneService cos;
	private final UtenteService us;
	private final FatturaService fs;
	Faker faker = new Faker(new Locale("it"));
	Random rnd = new Random();
	@Autowired
	PasswordEncoder bcrypt;

	@Autowired
	public RandomInstanceGenerator(ClienteService cs, IndirizzoService is, ComuneService cos, UtenteService us,
			FatturaService fs) {
		super();
		this.cs = cs;
		this.is = is;
		this.cos = cos;
		this.us = us;
		this.fs = fs;
	}

	public Utente randomUtenteGenerator(int numeroIstanze) {
		for (int i = 0; i < numeroIstanze; i++) {
			try {
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String username = faker.name().username();
				String email = faker.internet().emailAddress();
				String password = bcrypt.encode(faker.lorem().characters(6, 12));
				Ruolo role = Ruolo.values()[rnd.nextInt(Ruolo.values().length)];
				UtenteRequestPayload utente = new UtenteRequestPayload(name, surname, username, email, password, role);

				us.creaUtente(utente);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Errore nella generazione degli utenti!");
			}

		}
		return null;

	}

	public Cliente randomClienteGenerator(int numeroIstanze) {
		for (int i = 0; i < numeroIstanze; i++) {
			try {
				String ragione_sociale = faker.company().name();
				long partita_iva = this.generateRandomLongNumberSeries(11);
				String email = faker.internet().emailAddress();
				int rndYear = rnd.nextInt(22) + 2000;
				int rndMonth = rnd.nextInt(12) + 1;
				int rndMonth2 = rnd.nextInt(12) + 1;
				int rndDay = rnd.nextInt(28) + 1;
				int rndDay2 = rnd.nextInt(28) + 1;
				LocalDate data_inserimento = LocalDate.of(rndYear, rndMonth, rndDay);
				LocalDate data_ultimoContatto = LocalDate.of(rndYear + 1, rndMonth2, rndDay2);
				double fatturato = this.generateRandomFatturato();
				String pec = faker.internet().safeEmailAddress();
				String telefono = "0" + this.generateRandomNumberSeries(10);
				String email_contatto = faker.internet().emailAddress();
				String nome_contatto = faker.name().firstName();
				String cognome_contatto = faker.name().title() + " " + faker.name().lastName();
				long telefono_contatto = Long.parseLong("33" + this.generateRandomNumberSeries(8));
				Tipo_cliente tipo_cliente = Tipo_cliente.values()[rnd.nextInt(Tipo_cliente.values().length)];

				ClienteRequestPayload rndCliente = new ClienteRequestPayload(ragione_sociale, partita_iva, email,
						data_inserimento, data_ultimoContatto, fatturato, pec, telefono, email_contatto, nome_contatto,
						cognome_contatto, telefono_contatto, tipo_cliente);

				Cliente cliente = cs.create(rndCliente);
				cliente.setSedeLegale(this.randomIndirizzoGenerator(cliente));
				cliente.setSedeOperativa(this.randomIndirizzoGenerator(cliente));
				cs.update(cliente);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Errore nella generazione dei clienti!");

			}

		}
		return null;

	}

	public Indirizzo randomIndirizzoGenerator(Cliente cliente) {
		String via = faker.address().streetName();
		int chanceNumber = rnd.nextInt(100) + 1;
		String rndChar;
		if (chanceNumber <= 30) {
			rndChar = "A";
		} else if (chanceNumber > 30 && chanceNumber <= 55) {
			rndChar = "B";
		} else if (chanceNumber > 55 && chanceNumber <= 75) {
			rndChar = "C";
		} else if (chanceNumber > 75 && chanceNumber <= 90) {
			rndChar = "D";
		} else {
			rndChar = "E";
		}
		String numero_civico = rnd.nextInt(1000) + "/" + rndChar;
		String località = faker.ancient().titan();
		int cap = this.generateRandomNumberSeries(5);

		List<Comune> listaComuni = cos.findComuni();
		Comune comune = listaComuni.get(rnd.nextInt(listaComuni.size()));

		IndirizzoRequestPayload rndIndirizzo = new IndirizzoRequestPayload(via, numero_civico, località, cap, cliente,
				comune);

		return is.create(rndIndirizzo);

	}

	public Fattura randomFattureGenerator(int numeroIstanze) {
		for (int i = 0; i < numeroIstanze; i++) {
			try {
				List<Cliente> clienti = cs.findClienti();
				Cliente cliente = clienti.get(rnd.nextInt(clienti.size()));
				int annoInserimento = cliente.getDataInserimento().getYear();
				int range = LocalDate.now().getYear() - annoInserimento;
				int anno = annoInserimento + rnd.nextInt(range);
				int rndMonth = rnd.nextInt(12) + 1;
				int rndDay = rnd.nextInt(28) + 1;
				LocalDate data = LocalDate.of(anno, rndMonth, rndDay);
				double importo = this.generateRandomFatture();
				Stato_fattura stato_fattura = Stato_fattura.values()[rnd.nextInt(Stato_fattura.values().length)];
				FatturaRequestPayload rndFattura = new FatturaRequestPayload(anno, data, importo, stato_fattura,
						cliente);
				fs.create(rndFattura);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Errore nella generazione delle fatture!");
			}
		}
		return null;
	}

	public int generateRandomNumberSeries(int cifre) {
		int n = cifre;
		int min = (int) Math.pow(10, n - 1);
		int max = (int) Math.pow(10, n) - 1;
		int rndSeries = min + (int) (Math.random() * (max - min + 1));
		return rndSeries;
	}

	public long generateRandomLongNumberSeries(int cifre) {
		int n = cifre;
		long min = (long) Math.pow(10, n - 1);
		long max = (long) Math.pow(10, n) - 1;
		long rndSeries = min + (long) (Math.random() * (max - min + 1));
		return rndSeries;
	}

	public double generateRandomFatturato() {
		int fatturatoVero = rnd.nextInt(10000000) + 100000;
		double spiccioli = (rnd.nextInt(100) + 1) / 100.0;
		return fatturatoVero + spiccioli;
	}

	public double generateRandomFatture() {
		int fatturatoVero = rnd.nextInt(10000) + 1000;
		double spiccioli = (rnd.nextInt(100) + 1) / 100.0;
		return fatturatoVero + spiccioli;
	}

}
