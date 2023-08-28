package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import BuildWeek.Epic_Energy_Service.fattura.Fattura;
import BuildWeek.Epic_Energy_Service.indirizzo.Indirizzo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue
	private UUID cliente_id;
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
	@Enumerated(EnumType.STRING)
	private Tipo_cliente tipo_cliente;
	@OneToMany
	private List<Indirizzo> indirizzi;
	@OneToMany
	private List<Fattura> fatture;

	public Cliente(String ragione_sociale, long partita_iva, String email, LocalDate data_inserimento,
			LocalDate data_ultimoContatto, double fatturato_annuale, String pec, int telefono, String email_contatto,
			String nome_contatto, String cognome_contatto, String telefono_contatto, Tipo_cliente tipo_cliente) {
		this.ragione_sociale = ragione_sociale;
		this.partita_iva = partita_iva;
		this.email = email;
		this.data_inserimento = data_inserimento;
		this.data_ultimoContatto = data_ultimoContatto;
		this.fatturato_annuale = fatturato_annuale;
		this.pec = pec;
		this.telefono = telefono;
		this.email_contatto = email_contatto;
		this.nome_contatto = nome_contatto;
		this.cognome_contatto = cognome_contatto;
		this.telefono_contatto = telefono_contatto;
		this.tipo_cliente = tipo_cliente;
	}

}
