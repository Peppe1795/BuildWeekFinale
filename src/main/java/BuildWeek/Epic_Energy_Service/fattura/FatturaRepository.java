package BuildWeek.Epic_Energy_Service.fattura;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

	Page<Fattura> findByCliente(Cliente clienteId, Pageable pageable);

	Page<Fattura> findByStatoFattura(Stato_fattura statoFattura, Pageable pageable);

	Page<Fattura> findByData(LocalDate data, Pageable pageable);

	Page<Fattura> findByAnno(int anno, Pageable pageable);

	Page<Fattura> findByImportoBetween(double minImporto, double maxImporto, Pageable pageable);

}
