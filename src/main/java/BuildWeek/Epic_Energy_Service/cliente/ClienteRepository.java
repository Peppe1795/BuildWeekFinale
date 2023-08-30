package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	Optional<Cliente> findByFatturatoAnnuale(double fatturato_annuale);

	Optional<Cliente> findByDataInserimento(LocalDate dataInserimento);

	Optional<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto);

	List<Cliente> findByNomeContatto(String nomeContatto);

	Optional<Cliente> findByRagioneSociale(String ragioneSociale);

	List<Cliente> findAllByOrderByRagioneSociale();

	List<Cliente> findAllByOrderByFatturatoAnnuale();

	List<Cliente> findAllByOrderByDataInserimento();

	List<Cliente> findAllByOrderByDataUltimoContatto();

}

//mancano i metodi:
//ordinare per Provincia della sede legale.
//filtrare per Parte del nome