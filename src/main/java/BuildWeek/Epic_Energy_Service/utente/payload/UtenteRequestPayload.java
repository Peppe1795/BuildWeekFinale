package BuildWeek.Epic_Energy_Service.utente.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UtenteRequestPayload {
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
}
