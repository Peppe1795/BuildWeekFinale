package BuildWeek.Epic_Energy_Service.utente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import BuildWeek.Epic_Energy_Service.utente.payload.UtenteRequestPayload;

@RequestMapping("/utenti")
@RestController
public class UtenteController {
	private final UtenteService utenteService;

	@Autowired
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "userId") String sortBy) {
		return utenteService.find(page, size, sortBy);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/{userId}")
	public Utente findUtentiById(@PathVariable UUID userId) {
		return utenteService.findById(userId);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{userId}")
	public Utente updateUtenti(@PathVariable UUID userId, @RequestBody UtenteRequestPayload body) {
		return utenteService.findByIdAndUpdate(userId, body);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUtente(@PathVariable UUID userId) {
		utenteService.findByIdAndDelete(userId);
		return ResponseEntity.ok("Utente eliminato con successo.");
	}
}
