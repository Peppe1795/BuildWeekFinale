package BuildWeek.Epic_Energy_Service.comuni;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utenti/comuni")
public class ComuneController {
	private final ComuneService cs;

	@Autowired
	public ComuneController(ComuneService cs) {
		this.cs = cs;
	}

	@GetMapping
	public Page<Comune> findComuni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return cs.findComuniandPage(page, size, sortBy);
	}

	@GetMapping("/{denominazione}")
	public Comune findByDenominazione(String denominazione) {
		return cs.findByDenominazione(denominazione);
	}

	@GetMapping("/{id}")
	public Comune findById(UUID id) {
		return cs.findById(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public Comune createComune(Comune comune) {
		return cs.saveComune(comune);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteUtente(@PathVariable UUID id) {
		cs.findById(id);
		return ResponseEntity.ok("Comune eliminato con successo.");
	}
}
