package BuildWeek.Epic_Energy_Service.csvConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import BuildWeek.Epic_Energy_Service.province.Provincia;

@Component
public class CsvConverter {
	private Scanner scanner;

	public CsvConverter() {

	}

	public void convertCvs(String source) throws FileNotFoundException {
		scanner = new Scanner(new File(source));
		scanner.nextLine();
		scanner.useDelimiter(";");
		while (scanner.hasNext()) {
			String sigla = scanner.next();
			String nome_provincia = scanner.next();
			String nome_regione = scanner.next();

			Provincia provincia = new Provincia(sigla, nome_provincia, nome_regione);
			System.out.println(provincia);
		}
		scanner.close();
	}

}
