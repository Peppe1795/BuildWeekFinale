package BuildWeek.Epic_Energy_Service.utente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BuildWeek.Epic_Energy_Service.Exception.BadRequestException;
import BuildWeek.Epic_Energy_Service.Exception.NotFoundException;
import BuildWeek.Epic_Energy_Service.utente.payload.UtenteRequestPayload;

@Service
public class UtenteService {
	private final UtenteRepo utenteRepo;

	@Autowired
	public UtenteService(UtenteRepo utenteRepo) {
		this.utenteRepo = utenteRepo;
	}

	public Utente creaUtente(UtenteRequestPayload body) {
		utenteRepo.findByEmail(body.getEmail()).ifPresent(u -> {
			throw new BadRequestException("L'email è gia presente del database");
		});
		Utente newUtente = new Utente(body.getNome(), body.getCognome(), body.getUsername(), body.getEmail(),
				body.getPassword(), body.getRole());
		return utenteRepo.save(newUtente);
	}

	public Page<Utente> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		return utenteRepo.findAll(pageable);
	}

	public Utente findById(UUID id) throws NotFoundException {
		return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Utente findByIdAndUpdate(UUID id, UtenteRequestPayload body) throws NotFoundException {
		Utente found = this.findById(id);
		found.setNome(body.getNome());
		found.setCognome(body.getCognome());
		found.setUsername(body.getUsername());
		found.setEmail(body.getEmail());
		found.setPassword(body.getPassword());
		return utenteRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		utenteRepo.delete(found);
	}

	public Utente findByEmail(String email) {
		return utenteRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}

	public Utente findByUsername(String username) {
		return utenteRepo.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Username" + username + "non corrispondente"));
	}
}
