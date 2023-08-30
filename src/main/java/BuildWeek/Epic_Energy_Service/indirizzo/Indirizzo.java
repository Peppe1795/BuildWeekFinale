package BuildWeek.Epic_Energy_Service.indirizzo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import BuildWeek.Epic_Energy_Service.comuni.Comune;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor
public class Indirizzo {

	@Id
	@GeneratedValue
	private UUID indirizzoId;
	private String via;
	private String numeroCivico;
	private String località;
	private int cap;

	@JsonIgnore
	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Comune comune;

	public Indirizzo(String via, String numeroCivico, String località, int cap, Cliente cliente, Comune comune) {
		super();
		this.via = via;
		this.numeroCivico = numeroCivico;
		this.località = località;
		this.cap = cap;
		this.cliente = cliente;
		this.comune = comune;
	}

	@Override
	public String toString() {
		return "Indirizzo [indirizzoId=" + indirizzoId + ", via=" + via + ", numero_civico=" + numeroCivico
				+ ", località=" + località + ", cap=" + cap + ", comune=" + comune + "]";
	}

}
