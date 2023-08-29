package BuildWeek.Epic_Energy_Service.csvConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BuildWeek.Epic_Energy_Service.comuni.Comune;
import BuildWeek.Epic_Energy_Service.comuni.ComuneService;
import BuildWeek.Epic_Energy_Service.province.Provincia;

@Component
public class CsvConverter {
	@Autowired
	private ComuneService cs;
	private Scanner scanner;

	public CsvConverter() {

	}

	public void convertCvs(String source) throws FileNotFoundException {
		scanner = new Scanner(new File(source));
		scanner.nextLine();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			int counter = 0;

			if (parts.length == 3) {
				String sigla = parts[0];
				String nome_provincia = parts[1];
				String nome_regione = parts[2];

				Provincia provincia = new Provincia(sigla, nome_provincia, nome_regione);
				System.out.println(provincia);
			} else if (parts.length == 4) {
				int codice_provincia = Integer.parseInt(parts[0]);
				int progressivo_comune;
				if (!parts[1].equals("#RIF!")) {
					progressivo_comune = Integer.parseInt(parts[1]);
				} else {
					counter++;
					progressivo_comune = counter;
				}

				String denominazione = parts[2];
				String nome_provincia = parts[3];

				Comune comune = new Comune(codice_provincia, progressivo_comune, denominazione, nome_provincia);
				cs.saveComune(comune);
			}

		}
		scanner.close();
	}

}
