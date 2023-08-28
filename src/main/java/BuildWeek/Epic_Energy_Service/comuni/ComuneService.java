package BuildWeek.Epic_Energy_Service.comuni;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BuildWeek.Epic_Energy_Service.Exception.NotFoundException;

@Service
public class ComuneService {
	private final ComuneRepository cr;

	@Autowired
	public ComuneService(ComuneRepository cr) {

		this.cr = cr;
	}

	public Comune creaComune(Comune comune) {
		return cr.save(comune);
	}

	public Page<Comune> findComuniandPage(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return cr.findAll(pageable);
	}

	public List<Comune> findComuni() {
		return cr.findAll();
	}

	public Comune findByDenominazione(String denominazione) {
		return cr.findByDenominazione(denominazione).orElseThrow(
				() -> new NotFoundException("Nessun comune corrispondente a: " + denominazione + " è stato trovato."));
	}

	public Comune findById(UUID id) {
		return cr.findById(id)
				.orElseThrow(() -> new NotFoundException("Nessun comune con ID: " + id + " è stato trovato."));
	}

	public void deleteComune(String denominazione) {
		Comune comune = findByDenominazione(denominazione);
		cr.delete(comune);
	}

}
