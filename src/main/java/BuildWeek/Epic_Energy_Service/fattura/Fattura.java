package BuildWeek.Epic_Energy_Service.fattura;

import java.time.LocalDate;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fatture")
@Data
@NoArgsConstructor
public class Fattura {

	@Id
	@GeneratedValue(generator = "numero_fattura")
	@SequenceGenerator(name = "numero_fattura", initialValue = 1, allocationSize = 1)
	private int numeroFattura;
	private int anno;
	private LocalDate data;
	private double importo;

	@Enumerated(EnumType.STRING)
	private Stato_fattura statoFattura;
	@ManyToOne
	private Cliente cliente;

	public Fattura(int anno, LocalDate data, double importo, Stato_fattura stato_fattura, Cliente cliente) {
		this.anno = anno;
		this.data = data;
		this.importo = importo;
		this.statoFattura = stato_fattura;
		this.cliente = cliente;
	}

}
