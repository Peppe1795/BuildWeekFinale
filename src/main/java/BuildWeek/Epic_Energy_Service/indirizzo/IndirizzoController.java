package BuildWeek.Epic_Energy_Service.indirizzo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {

	private final IndirizzoService indirizzoSrv;

	@Autowired
	private IndirizzoController(IndirizzoService indirizzoSrv) {
		this.indirizzoSrv = indirizzoSrv;
	}

	@GetMapping
	public Page<Indirizzo> getFattura(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "clienteId") String sortBy) {
		return indirizzoSrv.find(page, size, sortBy);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Indirizzo saveIndirizzo(@RequestBody IndirizzoRequestPayload body) {
		Indirizzo created = indirizzoSrv.create(body);

		return created;
	}

	@GetMapping("/{indirizzoId}")
	public Indirizzo findById(@PathVariable UUID indirizzoId) {
		return indirizzoSrv.findById(indirizzoId);

	}

	@PutMapping("/{indirizzoId}")
	public Indirizzo updateIndirizzo(@PathVariable UUID indirizzoId, @RequestBody IndirizzoRequestPayload body) {
		return indirizzoSrv.findByIdAndUpdate(indirizzoId, body);
	}

	@DeleteMapping("/{indirizzoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIndirizzo(@PathVariable UUID indirizzoId) {
		indirizzoSrv.findByIdAndDelete(indirizzoId);
	}

}
