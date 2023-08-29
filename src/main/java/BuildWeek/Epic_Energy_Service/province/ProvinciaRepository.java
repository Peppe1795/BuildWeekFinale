package BuildWeek.Epic_Energy_Service.province;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
	Optional<Provincia> findByNome(String nome);

	Optional<Provincia> findBySigla(String sigla);
}
