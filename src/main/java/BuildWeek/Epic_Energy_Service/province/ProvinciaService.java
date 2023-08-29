package BuildWeek.Epic_Energy_Service.province;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
	private final ProvinciaRepository pr;

	@Autowired
	public ProvinciaService(ProvinciaRepository pr) {
		super();
		this.pr = pr;
	}

	public Provincia saveProvincia(Provincia provincia) {
		return pr.save(provincia);
	}

}
