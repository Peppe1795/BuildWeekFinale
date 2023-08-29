package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	Optional<Cliente> findByFatturato_annuale(double fatturato_annuale);

	Optional<Cliente> findByData_inserimento(LocalDate data_inserimento);

	Optional<Cliente> findByData_ultimoContatto(LocalDate data_ultimoContatto);

	Optional<Cliente> findByNome_contatto(String nome_contatto);
}
