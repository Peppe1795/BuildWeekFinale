package BuildWeek.Epic_Energy_Service.comuni;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comune {
	private int codice_provincia;
	private int progressivo_comune;
	private String denominazione;
	private String nome_provincia;

	public Comune(int codice_provincia, int progressivo_comune, String denominazione, String nome_provincia) {
		super();
		this.codice_provincia = codice_provincia;
		this.progressivo_comune = progressivo_comune;
		this.denominazione = denominazione;
		this.nome_provincia = nome_provincia;
	}

}
