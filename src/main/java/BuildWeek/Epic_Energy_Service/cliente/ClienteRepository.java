package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	Optional<Cliente> findByFatturatoAnnuale(double fatturato_annuale);

	Optional<Cliente> findByDataInserimento(LocalDate dataInserimento);

	Optional<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto);

	Page<Cliente> findByNomeContattoContainingIgnoreCase(String parteDelNome, Pageable page);

	Page<Cliente> findByRagioneSocialeContainingIgnoreCase(String parteRagioneSociale, Pageable page);

	Optional<Cliente> findByRagioneSociale(String ragioneSociale);

	List<Cliente> findAllByOrderByRagioneSociale();

	List<Cliente> findAllByOrderByFatturatoAnnuale();

	List<Cliente> findAllByOrderByDataInserimento();

	List<Cliente> findAllByOrderByDataUltimoContatto();

	// List<Cliente> findAllByOrderByProvinciaSedeLegaleAsc();
	@Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune ORDER BY nome_provincia")
	List<Cliente> findAllByOrderProvinciaSedeLegale();

	@Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune p WHERE p.nome_provincia = :nome_provincia")
	List<Cliente> findAllByProvinciaSedeLegale(@Param("nome_provincia") String nome_provincia);

}

//mancano i metodi:
//ordinare per Provincia della sede legale.
//filtrare per Parte del nome