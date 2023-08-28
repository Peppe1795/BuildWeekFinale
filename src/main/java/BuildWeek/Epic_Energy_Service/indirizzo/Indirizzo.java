package BuildWeek.Epic_Energy_Service.indirizzo;

import java.util.UUID;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import BuildWeek.Epic_Energy_Service.comuni.Comune;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Indirizzo {

	@Id
	@GeneratedValue
	private UUID indirizzo_id;
	private String via;
	private String numero_civico;
	private String località;
	private int cap;

	@ManyToOne
	private Comune comune;
	@ManyToOne
	private Cliente cliente;

	public Indirizzo(String via, String numero_civico, String località, int cap) {
		super();
		this.via = via;
		this.numero_civico = numero_civico;
		this.località = località;
		this.cap = cap;
	}

}