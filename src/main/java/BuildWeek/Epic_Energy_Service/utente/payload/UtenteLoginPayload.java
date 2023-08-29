package BuildWeek.Epic_Energy_Service.utente.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UtenteLoginPayload {
	String username;
	String email;
	String password;
}
