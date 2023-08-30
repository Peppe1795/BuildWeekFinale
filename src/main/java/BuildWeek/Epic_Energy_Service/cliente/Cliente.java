package BuildWeek.Epic_Energy_Service.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clienti")
@Getter
@Setter
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
	@Cascade(CascadeType.ALL)
	private Indirizzo sedeLegale;
	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Indirizzo sedeOperativa;
	@OneToMany
	private List<Fattura> fatture;

	public Cliente(String ragioneSociale, long partitaIva, String email, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, double fatturatoAnnuale, String pec, String telefono, String emailContatto,
			String nomeContatto, String cognomeContatto, long telefonoContatto, Tipo_cliente tipoCliente) {
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.telefono = telefono;
		this.emailContatto = emailContatto;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.tipo_cliente = tipoCliente;
	}

	@Override
	public String toString() {
		return "Cliente [clienteId=" + clienteId + ", ragioneSociale=" + ragioneSociale + ", partitaIva=" + partitaIva
				+ ", email=" + email + ", dataInserimento=" + dataInserimento + ", dataUltimoContatto="
				+ dataUltimoContatto + ", fatturatoAnnuale=" + fatturatoAnnuale + ", pec=" + pec + ", telefono="
				+ telefono + ", emailContatto=" + emailContatto + ", nomeContatto=" + nomeContatto
				+ ", cognomeContatto=" + cognomeContatto + ", telefonoContatto=" + telefonoContatto + ", tipo_cliente="
				+ tipo_cliente + "]";
	}

}
