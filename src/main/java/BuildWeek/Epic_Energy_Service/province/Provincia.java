package BuildWeek.Epic_Energy_Service.province;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "province")
@Getter
@Setter
@ToString
public class Provincia {
	@Id
	@GeneratedValue
	private UUID id;
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
