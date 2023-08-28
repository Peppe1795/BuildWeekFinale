package BuildWeek.Epic_Energy_Service.cliente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BuildWeek.Epic_Energy_Service.Exception.NotFoundException;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepo;

	@Autowired
	private ClienteService(ClienteRepository clienteRepo) {
		this.clienteRepo = clienteRepo;
	}

	public Cliente create(ClienteRequestPayload body) {
		Cliente nuovoCliente = new Cliente(body.getRagione_sociale(), body.getPartita_iva(), body.getEmail(),
				body.getData_inserimento(), body.getData_ultimoContatto(), body.getFatturato_annuale(), body.getPec(),
				body.getTelefono(), body.getEmail_contatto(), body.getNome_contatto(), body.getCognome_contatto(),
				body.getTelefono_contatto(), body.getTipo_cliente());
		return clienteRepo.save(nuovoCliente);
	}

	public Page<Cliente> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return clienteRepo.findAll(pageable);
	}

	public Cliente findById(UUID id) throws NotFoundException {
		return clienteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Cliente findByIdAndUpdate(UUID id, ClienteRequestPayload body) throws NotFoundException {
		Cliente found = this.findById(id);
		found.setRagione_sociale(body.getRagione_sociale());
		found.setPartita_iva(body.getPartita_iva());
		found.setEmail(body.getEmail());
		found.setData_inserimento(body.getData_inserimento());
		found.setData_ultimoContatto(body.getData_ultimoContatto());
		found.setFatturato_annuale(body.getFatturato_annuale());
		found.setPec(body.getPec());
		found.setTelefono(body.getTelefono());
		found.setEmail_contatto(body.getEmail_contatto());
		found.setNome_contatto(body.getNome_contatto());
		found.setCognome_contatto(body.getCognome_contatto());
		found.setTelefono_contatto(body.getTelefono_contatto());
		found.setTipo_cliente(body.getTipo_cliente());

		return clienteRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Cliente found = this.findById(id);
		clienteRepo.delete(found);
	}
}
