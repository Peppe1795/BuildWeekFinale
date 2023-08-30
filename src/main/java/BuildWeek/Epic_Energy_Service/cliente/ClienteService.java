package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

	public Cliente findByClienteId(UUID id) throws NotFoundException {
		return clienteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Cliente findByIdAndUpdate(UUID id, ClienteRequestPayload body) throws NotFoundException {
		Cliente found = this.findByClienteId(id);
		found.setRagioneSociale(body.getRagione_sociale());
		found.setPartitaIva(body.getPartita_iva());
		found.setEmail(body.getEmail());
		found.setDataInserimento(body.getData_inserimento());
		found.setDataUltimoContatto(body.getData_ultimoContatto());
		found.setFatturatoAnnuale(body.getFatturato_annuale());
		found.setPec(body.getPec());
		found.setTelefono(body.getTelefono());
		found.setEmailContatto(body.getEmail_contatto());
		found.setNomeContatto(body.getNome_contatto());
		found.setCognomeContatto(body.getCognome_contatto());
		found.setTelefonoContatto(body.getTelefono_contatto());
		found.setTipo_cliente(body.getTipo_cliente());

		return clienteRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Cliente found = this.findByClienteId(id);
		clienteRepo.delete(found);
	}

	public Optional<Cliente> findByFatturato_annuale(double fatturato) {
		return clienteRepo.findByFatturatoAnnuale(fatturato);
	}

	public Optional<Cliente> findByData_inserimento(LocalDate dataInserimento) {
		return clienteRepo.findByDataInserimento(dataInserimento);
	}

	public Optional<Cliente> findByData_ultimo_contatto(LocalDate dataUltimoContatto) {
		return clienteRepo.findByDataUltimoContatto(dataUltimoContatto);
	}

	public List<Cliente> findByNome_contatto(String nomeContatto) {
		return clienteRepo.findByNomeContatto(nomeContatto);
	}

	public Cliente update(Cliente cliente) {

		cliente.setRagioneSociale(cliente.getRagioneSociale());
		cliente.setPartitaIva(cliente.getPartitaIva());
		cliente.setEmail(cliente.getEmail());
		cliente.setDataInserimento(cliente.getDataInserimento());
		cliente.setDataUltimoContatto(cliente.getDataUltimoContatto());
		cliente.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
		cliente.setPec(cliente.getPec());
		cliente.setTelefono(cliente.getTelefono());
		cliente.setEmailContatto(cliente.getEmailContatto());
		cliente.setNomeContatto(cliente.getNomeContatto());
		cliente.setCognomeContatto(cliente.getCognomeContatto());
		cliente.setTelefonoContatto(cliente.getTelefonoContatto());
		cliente.setTipo_cliente(cliente.getTipo_cliente());
		cliente.setSedeLegale(cliente.getSedeLegale());
		cliente.setSedeOperativa(cliente.getSedeOperativa());

		return clienteRepo.save(cliente);
	}

	public Optional<Cliente> findByRagione_sociale(String ragioneSociale) {
		return clienteRepo.findByRagioneSociale(ragioneSociale);
	}

	public List<Cliente> findClienti() {
		return clienteRepo.findAll();
	}

	public List<Cliente> getListaClientiOrdinati(String criterioOrdinamento) {
		switch (criterioOrdinamento) {
		case "ragione_sociale":
			return clienteRepo.findAllByOrderByRagioneSociale();
		case "fatturato_annuale":
			return clienteRepo.findAllByOrderByFatturatoAnnuale();
		case "data_inserimento":
			return clienteRepo.findAllByOrderByDataInserimento();
		case "data_ultimoContatto":
			return clienteRepo.findAllByOrderByDataUltimoContatto();
//		case "provincia":
//			return clienteRepo.findAllByOrderBySedeLegale_Provincia();
		default:
			throw new IllegalArgumentException("Criterio di ordinamento non valido.");
		}
	}
}
