package BuildWeek.Epic_Energy_Service.fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BuildWeek.Epic_Energy_Service.Exception.NotFoundException;
import BuildWeek.Epic_Energy_Service.cliente.Cliente;

@Service
public class FatturaService {

	private final FatturaRepository fatturaRepo;

	@Autowired
	private FatturaService(FatturaRepository fatturaRepo) {
		this.fatturaRepo = fatturaRepo;
	}

	public Fattura create(FatturaRequestPayload body) {
		Fattura nuovaFattura = new Fattura(body.getAnno(), body.getData(), body.getImporto(), body.getStato_fattura(),
				body.getCliente());
		return fatturaRepo.save(nuovaFattura);
	}

	public Page<Fattura> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return fatturaRepo.findAll(pageable);
	}

	public Fattura findById(int numeroFattura) throws NotFoundException {
		return fatturaRepo.findById(numeroFattura).orElseThrow(() -> new NotFoundException(numeroFattura));
	}

	public Fattura findByIdAndUpdate(int numeroFattura, FatturaRequestPayload body) throws NotFoundException {
		Fattura found = this.findById(numeroFattura);
		found.setAnno(body.getAnno());
		found.setData(body.getData());
		found.setImporto(body.getImporto());
		found.setStatoFattura(body.getStato_fattura());
		found.setCliente(body.getCliente());

		return fatturaRepo.save(found);
	}

	public void findByIdAndDelete(int numeroFattura) throws NotFoundException {
		Fattura found = this.findById(numeroFattura);
		fatturaRepo.delete(found);
	}

	public List<Fattura> findByStato_Fattura(Stato_fattura statoFattura) {
		return fatturaRepo.findByStatoFattura(statoFattura);
	}

	public List<Fattura> findByData(LocalDate data) {
		return fatturaRepo.findByData(data);
	}

	public List<Fattura> findByAnno(int anno) {
		return fatturaRepo.findByAnno(anno);
	}

	public Optional<Fattura> findByCliente(Cliente clienteId) {
		return fatturaRepo.findByCliente(clienteId);
	}

	public List<Fattura> findByImportoRange(double minImporto, double maxImporto) {
		return fatturaRepo.findByImportoBetween(minImporto, maxImporto);
	}
}
