package BuildWeek.Epic_Energy_Service.utente.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UtenteRequestPayload {
	private String nome;
	private String cognome;
	private String user_name;
	private String email;
	private String password;
}
