package BuildWeek.Epic_Energy_Service.utente.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class UtenteRequestPayload {
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
}
