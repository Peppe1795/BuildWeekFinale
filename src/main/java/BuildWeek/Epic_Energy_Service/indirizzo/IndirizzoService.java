package BuildWeek.Epic_Energy_Service.indirizzo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BuildWeek.Epic_Energy_Service.Exception.NotFoundException;

@Service
public class IndirizzoService {

	private final IndirizzoRepository indirizzoRepo;

	@Autowired
	private IndirizzoService(IndirizzoRepository indirizzoRepo) {
		this.indirizzoRepo = indirizzoRepo;
	}

	public Indirizzo create(IndirizzoRequestPayload body) {
		Indirizzo nuovoIndirizzo = new Indirizzo(body.getVia(), body.getNumero_civico(), body.getLocalità(),
				body.getCap(), body.getCliente(), body.getComune());
		return indirizzoRepo.save(nuovoIndirizzo);
	}

	public Page<Indirizzo> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return indirizzoRepo.findAll(pageable);
	}

	public Indirizzo findById(UUID id) throws NotFoundException {
		return indirizzoRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Indirizzo findByIdAndUpdate(UUID id, IndirizzoRequestPayload body) throws NotFoundException {
		Indirizzo found = this.findById(id);
		found.setVia(body.getVia());
		found.setNumeroCivico(body.getNumero_civico());
		found.setLocalità(body.getLocalità());
		found.setCap(body.getCap());
		found.setCliente(body.getCliente());

		return indirizzoRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Indirizzo found = this.findById(id);
		indirizzoRepo.delete(found);
	}
}
