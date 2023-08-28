package BuildWeek.Epic_Energy_Service.province;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Provincia {
	String sigla;
	String nome_provincia;
	String nome_regione;

	public Provincia(String sigla, String nome_provincia, String nome_regione) {
		super();
		this.sigla = sigla;
		this.nome_provincia = nome_provincia;
		this.nome_regione = nome_regione;
	}

}
