package BuildWeek.Epic_Energy_Service.utente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "userId") String sortBy) {
		return utenteService.find(page, size, sortBy);
	}

	@GetMapping("/{userId}")
	public Utente findUtentiById(@PathVariable UUID userId) {
		return utenteService.findById(userId);

	}

	@PutMapping("/{userId}")
	public Utente updateUtenti(@PathVariable UUID userId, @RequestBody UtenteRequestPayload body) {
		return utenteService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUtente(@PathVariable UUID userId) {
		utenteService.findByIdAndDelete(userId);
	}
}
