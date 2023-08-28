package BuildWeek.Epic_Energy_Service.comuni;

import java.util.List;
import java.util.UUID;

import BuildWeek.Epic_Energy_Service.indirizzo.Indirizzo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@ToString
public class Comune {
	@Id
	@GeneratedValue
	private UUID id;
	private int codice_provincia;
	private int progressivo_comune;
	private String denominazione;
	private String nome_provincia;

	@OneToMany(mappedBy = "comune")
	private List<Indirizzo> indirizzi;

	public Comune(int codice_provincia, int progressivo_comune, String denominazione, String nome_provincia) {
		super();
		this.codice_provincia = codice_provincia;
		this.progressivo_comune = progressivo_comune;
		this.denominazione = denominazione;
		this.nome_provincia = nome_provincia;
	}

}
