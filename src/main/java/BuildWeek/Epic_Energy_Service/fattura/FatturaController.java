package BuildWeek.Epic_Energy_Service.fattura;

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
@RequestMapping("/fattura")
public class FatturaController {

	private final FatturaService fatturaSrv;

	@Autowired
	private FatturaController(FatturaService fatturaSrv) {
		this.fatturaSrv = fatturaSrv;
	}

	@GetMapping
	public Page<Fattura> getFattura(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "clienteId") String sortBy) {
		return fatturaSrv.find(page, size, sortBy);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura saveFattura(@RequestBody FatturaRequestPayload body) {
		Fattura created = fatturaSrv.create(body);

		return created;
	}

	@GetMapping("/{fatturaId}")
	public Fattura findById(@PathVariable UUID fatturaId) {
		return fatturaSrv.findById(fatturaId);

	}

	@PutMapping("/{fatturaId}")
	public Fattura updateFattura(@PathVariable UUID fatturaId, @RequestBody FatturaRequestPayload body) {
		return fatturaSrv.findByIdAndUpdate(fatturaId, body);
	}

	@DeleteMapping("/{fatturaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFattura(@PathVariable UUID fatturaId) {
		fatturaSrv.findByIdAndDelete(fatturaId);
	}

}
