package BuildWeek.Epic_Energy_Service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import BuildWeek.Epic_Energy_Service.Exception.UnauthorizedException;
import BuildWeek.Epic_Energy_Service.utente.Utente;
import BuildWeek.Epic_Energy_Service.utente.UtenteService;
import BuildWeek.Epic_Energy_Service.utente.payload.UtenteLoginPayload;
import BuildWeek.Epic_Energy_Service.utente.payload.UtenteRequestPayload;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UtenteService utenteService;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder bcrypt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser(@RequestBody UtenteRequestPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		Utente created = utenteService.creaUtente(body);
		return created;
	}

	@PostMapping("/login")

	public ResponseEntity<String> login(@RequestBody UtenteLoginPayload body) {

		Utente utente = null;

		if (body.getEmail() != null) {
			utente = utenteService.findByEmail(body.getEmail());
		} else {
			utente = utenteService.findByUsername(body.getUsername());
		}

		if (utente != null && bcrypt.matches(body.getPassword(), utente.getPassword())) {
			String token = jwtTools.creaToken(utente);
			return new ResponseEntity<>(token, HttpStatus.OK);

		} else {
			throw new UnauthorizedException("Credenziali non valide");
		}
	}
}
