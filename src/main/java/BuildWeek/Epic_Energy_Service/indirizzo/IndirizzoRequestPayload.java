package BuildWeek.Epic_Energy_Service.indirizzo;

import BuildWeek.Epic_Energy_Service.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IndirizzoRequestPayload {
	private String via;
	private String numero_civico;
	private String località;
	private int cap;
	private Cliente cliente;

}
