package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	Optional<Cliente> findByFatturatoAnnuale(double fatturatoAnnuale);

	Optional<Cliente> findByDataInserimento(LocalDate dataInserimento);

	Optional<Cliente> findByDataUltimoContatto(LocalDate dataIltimoContatto);

	Optional<Cliente> findByNomeContatto(String nomeContatto);

	Optional<Cliente> findByRagioneSociale(String ragioneSociale);

	List<Cliente> findAllByOrderByRagioneSociale();

	List<Cliente> findAllByOrderByFatturatoAnnuale();

	List<Cliente> findAllByOrderByDataInserimento();

	List<Cliente> findAllByOrderByDataUltimoContatto();

}
