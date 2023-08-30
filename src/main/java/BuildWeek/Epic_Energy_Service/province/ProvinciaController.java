package BuildWeek.Epic_Energy_Service.province;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utenti/province")
public class ProvinciaController {
	private final ProvinciaService ps;

	@Autowired
	public ProvinciaController(ProvinciaService ps) {
		this.ps = ps;
	}

	@GetMapping
	public Page<Provincia> findProvince(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return ps.findProvinceandPage(page, size, sortBy);
	}

	@GetMapping("/{nome_provincia}")
	public Provincia findByNome(String nome_provincia) {
		return ps.findByNome(nome_provincia);
	}

	@GetMapping("/{sigla}")
	public Provincia findBySigla(String sigla) {
		return ps.findBySigla(sigla);
	}

	@PostMapping
	public Provincia createProvincia(Provincia provincia) {
		return ps.saveProvincia(provincia);
	}

}
