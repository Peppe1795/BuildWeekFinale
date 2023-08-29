package BuildWeek.Epic_Energy_Service.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Fattura {

	@Id
	@GeneratedValue
	private UUID fatturaId;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private int numero;
	@Enumerated(EnumType.STRING)
	private Stato_fattura stato_fattura;
	@ManyToOne
	private Cliente cliente;

	public Fattura(int anno, LocalDate data, BigDecimal importo, int numero, Stato_fattura stato_fattura) {
		this.anno = anno;
		this.data = data;
		this.importo = importo;
		this.numero = numero;
		this.stato_fattura = stato_fattura;
	}

}
