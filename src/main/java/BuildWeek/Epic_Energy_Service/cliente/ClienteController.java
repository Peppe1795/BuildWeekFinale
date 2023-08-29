package BuildWeek.Epic_Energy_Service.cliente;

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
@RequestMapping("/cliente")
public class ClienteController {
	private final ClienteService clienteSrv;

	@Autowired
	private ClienteController(ClienteService clienteSrv) {
		this.clienteSrv = clienteSrv;
	}

	@GetMapping
	public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "clienteId") String sortBy) {
		return clienteSrv.find(page, size, sortBy);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody ClienteRequestPayload body) {
		Cliente created = clienteSrv.create(body);

		return created;
	}

	@GetMapping("/{clienteId}")
	public Cliente findById(@PathVariable UUID clienteId) {
		return clienteSrv.findById(clienteId);

	}

	@PutMapping("/{clienteId}")
	public Cliente updateCliente(@PathVariable UUID clienteId, @RequestBody ClienteRequestPayload body) {
		return clienteSrv.findByIdAndUpdate(clienteId, body);
	}

	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable UUID clienteId) {
		clienteSrv.findByIdAndDelete(clienteId);
	}

}
