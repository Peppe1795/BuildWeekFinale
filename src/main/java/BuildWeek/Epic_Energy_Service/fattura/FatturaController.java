package BuildWeek.Epic_Energy_Service.fattura;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import BuildWeek.Epic_Energy_Service.cliente.Cliente;

@RestController
@RequestMapping("/utenti/fattura")
public class FatturaController {

	private final FatturaService fatturaSrv;

	@Autowired
	public FatturaController(FatturaService fatturaSrv) {
		this.fatturaSrv = fatturaSrv;
	}

	@GetMapping
	public Page<Fattura> getFattura(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "numeroFattura") String sortBy) {
		return fatturaSrv.find(page, size, sortBy);
	}

	@GetMapping("/{numeroFattura}")
	public Fattura findById(@PathVariable int numeroFattura) {
		return fatturaSrv.findById(numeroFattura);

	}

	@GetMapping("/statoFattura")
	public ResponseEntity<Page<Fattura>> getFattureByStatoFattura(@RequestParam Stato_fattura statoFattura,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "nomeContatto") String sortBy) {
		Page<Fattura> fattureByStatoFattura = fatturaSrv.findByStato_Fattura(statoFattura, page, size, sortBy);

		if (fattureByStatoFattura.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(fattureByStatoFattura);
		}
	}

	@GetMapping("/anno")
	public ResponseEntity<Page<Fattura>> getFattureByAnno(@RequestParam int anno,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "nomeContatto") String sortBy) {
		Page<Fattura> fattureByAnno = fatturaSrv.findByAnno(anno, page, size, sortBy);

		if (fattureByAnno.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(fattureByAnno);
		}
	}

	@GetMapping("/data")
	public ResponseEntity<Page<Fattura>> getFattureByData(@RequestParam LocalDate data,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "nomeContatto") String sortBy) {
		Page<Fattura> fattureByData = fatturaSrv.findByData(data, page, size, sortBy);

		if (fattureByData.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(fattureByData);
		}
	}

	@GetMapping("/clienteId")
	public ResponseEntity<Optional<Fattura>> getFattureByCliente(@RequestParam Cliente clienteId) {
		Optional<Fattura> clientiByCliente = fatturaSrv.findByCliente(clienteId);
		return ResponseEntity.ok(clientiByCliente);
	}

	@GetMapping("/importo")
	public ResponseEntity<Page<Fattura>> getFattureByImportoRange(@RequestParam double minImporto,
			@RequestParam double maxImporto, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nomeContatto") String sortBy) {
		Page<Fattura> fattureByImportoRange = fatturaSrv.findByImportoRange(minImporto, maxImporto, page, size, sortBy);

		if (fattureByImportoRange.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(fattureByImportoRange);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura saveFattura(@RequestBody FatturaRequestPayload body) {
		Fattura created = fatturaSrv.create(body);

		return created;
	}

	@PutMapping("/{numeroFattura}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Fattura updateFattura(@PathVariable int numeroFattura, @RequestBody FatturaRequestPayload body) {
		return fatturaSrv.findByIdAndUpdate(numeroFattura, body);
	}

	@DeleteMapping("/{numeroFattura}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteFattura(@PathVariable int numeroFattura) {
		fatturaSrv.findByIdAndDelete(numeroFattura);
		return ResponseEntity.ok("Fattura eliminate con successo.");
	}
}
