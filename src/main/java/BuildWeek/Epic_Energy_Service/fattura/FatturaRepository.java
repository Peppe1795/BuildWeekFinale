package BuildWeek.Epic_Energy_Service.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;

public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
	Optional<Fattura> findByCliente(Cliente cliente);

	Optional<Fattura> findByStatoFattura(Stato_fattura statoFattura);

	Optional<Fattura> findByData(LocalDate data);

	Optional<Fattura> findByAnno(int anno);

	Optional<Fattura> findByImporto(BigDecimal importo);
}
