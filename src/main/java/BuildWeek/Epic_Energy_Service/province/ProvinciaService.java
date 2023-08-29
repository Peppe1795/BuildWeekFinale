package BuildWeek.Epic_Energy_Service.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import BuildWeek.Epic_Energy_Service.Exception.NotFoundException;

@Service
public class ProvinciaService {
	private final ProvinciaRepository pr;

	@Autowired
	public ProvinciaService(ProvinciaRepository pr) {
		this.pr = pr;
	}

	public Provincia saveProvincia(Provincia provincia) {
		return pr.save(provincia);
	}

	public Page<Provincia> findProvinceandPage(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return pr.findAll(pageable);
	}

	public List<Provincia> findProvince() {
		return pr.findAll();
	}

	public Provincia findByNome(String nome) {
		return pr.findByNome(nome).orElseThrow(
				() -> new NotFoundException("Nessun comune corrispondente a: " + nome + " è stato trovato."));
	}

	public Provincia findBySigla(String sigla) {
		return pr.findBySigla(sigla).orElseThrow(
				() -> new NotFoundException("Nessun comune corrispondente a: " + sigla + " è stato trovato."));
	}

	public void deleteProvincia(String nome_provincia) {
		Provincia provincia = findByNome(nome_provincia);
		pr.delete(provincia);
	}

}
