package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	Optional<Cliente> findByFatturatoAnnuale(double fatturatoAnnuale);

	Optional<Cliente> findByDataInserimento(LocalDate dataInserimento);

	Optional<Cliente> findByDataUltimoContatto(LocalDate dataIltimoContatto);

	Optional<Cliente> findByNomeContatto(String nomeContatto);
}
