package BuildWeek.Epic_Energy_Service.indirizzo;

import java.util.UUID;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import BuildWeek.Epic_Energy_Service.comuni.Comune;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "indirizzi")
@Data
@NoArgsConstructor
public class Indirizzo {

	@Id
	@GeneratedValue
	private UUID indirizzoId;
	private String via;
	private String numero_civico;
	private String località;
	private int cap;

	@ManyToOne
	private Cliente cliente;

	@OneToMany
	private Comune coumne;

	public Indirizzo(String via, String numero_civico, String località, int cap) {
		this.via = via;
		this.numero_civico = numero_civico;
		this.località = località;
		this.cap = cap;
	}

}
