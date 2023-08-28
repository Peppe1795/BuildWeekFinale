package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteRequestPayload {
	private String ragione_sociale;
	private long partita_iva;
	private String email;
	private LocalDate data_inserimento;
	private LocalDate data_ultimoContatto;
	private double fatturato_annuale;
	private String pec;
	private int telefono;
	private String email_contatto;
	private String nome_contatto;
	private String cognome_contatto;
	private String telefono_contatto;
	private Tipo_cliente tipo_cliente;
}
