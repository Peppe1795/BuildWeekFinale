package BuildWeek.Epic_Energy_Service.utente.payload;

import BuildWeek.Epic_Energy_Service.utente.Ruolo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class UtenteRequestPayload {
	@NotNull(message = "Il nome è obbligatorio")
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	private Ruolo role;
}
