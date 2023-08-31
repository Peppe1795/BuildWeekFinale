package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

@RestController
@RequestMapping("/utenti/cliente")
public class ClienteController {
	private final ClienteService clienteSrv;

	@Autowired
	public ClienteController(ClienteService clienteSrv) {
		this.clienteSrv = clienteSrv;
	}

	@GetMapping
	public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "clienteId") String sortBy) {
		return clienteSrv.find(page, size, sortBy);
	}

	@GetMapping("/ordinati")
	public ResponseEntity<List<Cliente>> getListaClientiOrdinati(@RequestParam(name = "ordinati") String criterio) {
		List<Cliente> clientiOrdinati = clienteSrv.getListaClientiOrdinati(criterio);
		return ResponseEntity.ok(clientiOrdinati);
	}

	@GetMapping("/clienteId")
	public Cliente findById(@PathVariable UUID clienteId) {
		return clienteSrv.findByClienteId(clienteId);

	}

	@GetMapping("/fatturato")
	public ResponseEntity<Optional<Cliente>> getClientiByFatturato(@RequestParam double fatturato) {
		Optional<Cliente> clientiByFatturato = clienteSrv.findByFatturato_annuale(fatturato);
		return ResponseEntity.ok(clientiByFatturato);
	}

	@GetMapping("/dataInserimento")
	public ResponseEntity<Optional<Cliente>> getClientiByDataInserimento(@RequestParam LocalDate dataInserimento) {
		Optional<Cliente> clientiByDataInserimento = clienteSrv.findByData_inserimento(dataInserimento);
		return ResponseEntity.ok(clientiByDataInserimento);
	}

	@GetMapping("/dataUltimoContatto")
	public ResponseEntity<Optional<Cliente>> getClientiByDataUltimoContatto(
			@RequestParam LocalDate dataUltimoContatto) {
		Optional<Cliente> clientiBydataUltimoContatto = clienteSrv.findByData_ultimo_contatto(dataUltimoContatto);
		return ResponseEntity.ok(clientiBydataUltimoContatto);
	}

	@GetMapping("/parteDelNome")
	public ResponseEntity<Page<Cliente>> getClientiByParteDelNome(@RequestParam String parteDelNome,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "nomeContatto") String sortBy) {
		Page<Cliente> clientiByParteDelNome = clienteSrv.findByParteDelNome(parteDelNome, page, size, sortBy);

		if (clientiByParteDelNome.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(clientiByParteDelNome);
		}
	}

	@GetMapping("/parteRagioneSociale")
	public ResponseEntity<Page<Cliente>> getClientiByParteRagioneSociale(@RequestParam String parteRagioneSociale,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "ragioneSociale") String sortBy) {
		Page<Cliente> clientiByParteRagioneSociale = clienteSrv.findByParteRagioneSociale(parteRagioneSociale, page,
				size, sortBy);

		if (clientiByParteRagioneSociale.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(clientiByParteRagioneSociale);
		}
	}

	@GetMapping("/ragioneSociale")
	public ResponseEntity<Optional<Cliente>> getClientiByRagioneSociale(@RequestParam String ragioneSociale) {
		Optional<Cliente> clientiByragioneSociale = clienteSrv.findByRagione_sociale(ragioneSociale);
		return ResponseEntity.ok(clientiByragioneSociale);
	}

	@PutMapping("/{clienteId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cliente updateCliente(@PathVariable UUID clienteId, @RequestBody ClienteRequestPayload body) {
		return clienteSrv.findByIdAndUpdate(clienteId, body);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody ClienteRequestPayload body) {
		Cliente created = clienteSrv.create(body);
		return created;
	}

	@DeleteMapping("/{clienteId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCliente(@PathVariable UUID clienteId) {
		clienteSrv.findByIdAndDelete(clienteId);
		return ResponseEntity.ok("Cliente eliminato con successo.");
	}

}
