package BuildWeek.Epic_Energy_Service.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FatturaRequestPayload {
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private int numero;
	private Stato_fattura stato_fattura;
	private Cliente cliente;
}
