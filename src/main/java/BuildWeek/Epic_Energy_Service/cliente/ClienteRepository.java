package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
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

	Page<Cliente> findAllByOrderByRagioneSociale(Pageable page);

	Page<Cliente> findAllByOrderByFatturatoAnnuale(Pageable page);

	Page<Cliente> findAllByOrderByDataInserimento(Pageable page);

	Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable page);

	@Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune ORDER BY nome_provincia")
	Page<Cliente> findAllByOrderProvinciaSedeLegale(Pageable page);

	@Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune p WHERE p.nome_provincia = :nome_provincia")
	Page<Cliente> findAllByProvinciaSedeLegale(@Param("nome_provincia") String nome_provincia, Pageable page);

}
