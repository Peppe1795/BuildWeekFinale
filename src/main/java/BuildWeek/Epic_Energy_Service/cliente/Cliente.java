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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clienti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue
	private UUID clienteId;
	private String ragioneSociale;
	private long partitaIva;
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private long telefonoContatto;
	@Enumerated(EnumType.STRING)
	private Tipo_cliente tipo_cliente;
	@ManyToOne
	private Indirizzo sedeLegale;
	@ManyToOne
	private Indirizzo sedeOperativa;
	@OneToMany
	private List<Fattura> fatture;

	public Cliente(String ragione_sociale, long partita_iva, String email, LocalDate data_inserimento,
			LocalDate data_ultimoContatto, double fatturato_annuale, String pec, String telefono, String email_contatto,
			String nome_contatto, String cognome_contatto, long telefono_contatto, Tipo_cliente tipo_cliente) {
		this.ragioneSociale = ragione_sociale;
		this.partitaIva = partita_iva;
		this.email = email;
		this.dataInserimento = data_inserimento;
		this.dataUltimoContatto = data_ultimoContatto;
		this.fatturatoAnnuale = fatturato_annuale;
		this.pec = pec;
		this.telefono = telefono;
		this.emailContatto = email_contatto;
		this.nomeContatto = nome_contatto;
		this.cognomeContatto = cognome_contatto;
		this.telefonoContatto = telefono_contatto;
		this.tipo_cliente = tipo_cliente;
	}

}
