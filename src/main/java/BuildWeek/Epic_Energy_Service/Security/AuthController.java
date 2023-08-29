package BuildWeek.Epic_Energy_Service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BuildWeek.Epic_Energy_Service.Exception.UnauthorizedException;
import BuildWeek.Epic_Energy_Service.utente.Utente;
import BuildWeek.Epic_Energy_Service.utente.UtenteService;
import BuildWeek.Epic_Energy_Service.utente.payload.UtenteRequestPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UtenteService utenteService;

	@Autowired
	JWTTools jwtTools;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser(@RequestBody UtenteRequestPayload body) {
		Utente created = utenteService.creaUtente(body);
		return created;
	}

	@PostMapping("/login")

	public ResponseEntity<String> login(@RequestBody UtenteRequestPayload body) {

		Utente utente = utenteService.findByEmail(body.getEmail());

		if (body.getPassword().equals(utente.getPassword())) {

			String token = jwtTools.creaToken(utente);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} else {
			throw new UnauthorizedException("Credenziali non valide");
		}
	}
}
