package BuildWeek.Epic_Energy_Service.utente;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue
	private UUID utente_id;
	private String user_name;
	private String email;
	private String nome;
	private String cognome;
	private String password;
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;

	public Utente(String user_name, String email, String nome, String cognome, String password, Ruolo ruolo) {
		this.user_name = user_name;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.ruolo = ruolo;
	}

}
