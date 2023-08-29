package BuildWeek.Epic_Energy_Service.utente.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import BuildWeek.Epic_Energy_Service.csvConverter.CsvConverter;

@Component
public class CsvConverterRunner implements CommandLineRunner {
	@Autowired
	CsvConverter converter;

	@Override
	public void run(String... args) throws Exception {
//
//		converter.convertCvs("comuni-italiani.csv");
//		converter.convertCvs("province-italiane.csv");
//		converter.linkProvinceEComuni();

	}

}
