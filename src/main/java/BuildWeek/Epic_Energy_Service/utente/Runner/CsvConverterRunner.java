package BuildWeek.Epic_Energy_Service.utente.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import BuildWeek.Epic_Energy_Service.cliente.ClienteService;
import BuildWeek.Epic_Energy_Service.csvConverter.CsvConverter;

@Component
@Order(1)
public class CsvConverterRunner implements CommandLineRunner {
	@Autowired
	CsvConverter converter;

	@Autowired
	ClienteService cs;

	@Override
	public void run(String... args) throws Exception {

//		Thread csvConverterThread = new Thread(() -> {
//			try {
//				converter.convertCvs("comuni-italiani.csv");
//				converter.convertCvs("province-italiane.csv");
//			} catch (FileNotFoundException e) {
//
//				e.printStackTrace();
//			} finally {
//				Thread.currentThread().interrupt();
//			}
//
//		});
//		csvConverterThread.start();
//		csvConverterThread.join();
//		converter.linkProvinceEComuni();

		// *********QUESTO è UN CONTROLLO PER LA QUERY PER ORDINARE IN BASE ALLE
		// PROVINCE --->>> DA CANCELLARE ***************
//		List<Cliente> clienti = cs.orderByProvinciaSedeLegale();
//
//		for (Cliente cliente : clienti) {
//			System.out.println(cliente.getSedeLegale().getComune().getNome_provincia());
//		}
		// *********QUESTO è UN CONTROLLO PER LA QUERY PER FILTRARE IN BASE ALLE
		// PROVINCE --->>> DA CANCELLARE ***************
//		List<Cliente> clienti = cs.filterByProvincia("Roma");
//		for (Cliente cliente : clienti) {
//			System.out.println(
//					cliente.getRagioneSociale() + " " + cliente.getSedeLegale().getComune().getNome_provincia());
//		}
	}

}
