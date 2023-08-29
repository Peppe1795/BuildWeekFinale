package BuildWeek.Epic_Energy_Service.csvConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BuildWeek.Epic_Energy_Service.comuni.Comune;
import BuildWeek.Epic_Energy_Service.comuni.ComuneService;
import BuildWeek.Epic_Energy_Service.province.Provincia;
import BuildWeek.Epic_Energy_Service.province.ProvinciaService;

@Component
public class CsvConverter {

	private final ComuneService cs;
	private final ProvinciaService ps;
	private Scanner scanner;

	@Autowired
	public CsvConverter(ComuneService cs, ProvinciaService ps) {
		super();
		this.cs = cs;
		this.ps = ps;
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
				if (nome_provincia.equals("Verbania")) {
					nome_provincia = "Verbano-Cusio-Ossola";
				} else if (nome_provincia.equals("Carbonia Iglesias") || nome_provincia.equals("Medio Campidano")
						|| nome_provincia.equals("Ogliastra") || nome_provincia.equals("Olbia Tempio")) {
					nome_provincia = "Sud Sardegna";
				}

				Provincia provincia = new Provincia(sigla, nome_provincia, nome_regione);
				ps.saveProvincia(provincia);
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

				if (nome_provincia.equals("Ascoli Piceno")) {
					nome_provincia = "Ascoli-Piceno";
				} else if (nome_provincia.startsWith("Valle d'Aosta")) {
					nome_provincia = "Aosta";
				} else if (nome_provincia.equals("Pesaro e Urbino")) {
					nome_provincia = "Pesaro-Urbino";
				} else if (nome_provincia.equals("La Spezia")) {
					nome_provincia = "La-Spezia";
				} else if (nome_provincia.equals("Monza e della Brianza")) {
					nome_provincia = "Monza-Brianza";
				} else if (nome_provincia.equals("Vibo Valentia")) {
					nome_provincia = "Vibo-Valentia";
				} else if (nome_provincia.equals("Reggio nell'Emilia")) {
					nome_provincia = "Reggio-Emilia";
				} else if (nome_provincia.equals("Reggio Calabria")) {
					nome_provincia = "Reggio-Calabria";
				} else if (nome_provincia.equals("Bolzano/Bozen")) {
					nome_provincia = "Bolzano";
				} else if (nome_provincia.equals("Forlì-Cesena")) {
					nome_provincia = "Forli-Cesena";
				}

				Comune comune = new Comune(codice_provincia, progressivo_comune, denominazione, nome_provincia);
				cs.saveComune(comune);
			}

		}
		scanner.close();
	}

	public void linkProvinceEComuni() {
		Map<String, Provincia> provinciaMapped = new HashMap<>();
		List<Provincia> province = ps.findProvince();
		for (Provincia provincia : province) {
			provinciaMapped.put(provincia.getNome(), provincia);
		}

		List<Comune> comuni = cs.findComuni();
		for (Comune comune : comuni) {
			String nomeProvincia = comune.getNome_provincia();
			Provincia provincia = provinciaMapped.get(nomeProvincia);
			comune.setProvincia(provincia);
			cs.saveComune(comune);
		}
	}

}
