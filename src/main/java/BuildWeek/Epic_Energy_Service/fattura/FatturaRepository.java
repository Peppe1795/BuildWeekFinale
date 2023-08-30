package BuildWeek.Epic_Energy_Service.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

	Optional<Fattura> findByCliente(Cliente clienteId);

	List<Fattura> findByStatoFattura(Stato_fattura statoFattura);

	List<Fattura> findByData(LocalDate data);

	List<Fattura> findByAnno(int anno);

// questo metodo non va bene si deve filtrare per range di importi.
	Optional<Fattura> findByImporto(BigDecimal importo);
}
