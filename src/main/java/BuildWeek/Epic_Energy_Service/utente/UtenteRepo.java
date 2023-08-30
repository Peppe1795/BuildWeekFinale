package BuildWeek.Epic_Energy_Service.utente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, UUID> {
	Optional<Utente> findByEmail(String email);

	Optional<Utente> findByUsername(String username);

}
