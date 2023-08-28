package BuildWeek.Epic_Energy_Service.Security;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import BuildWeek.Epic_Energy_Service.Exception.UnauthorizedException;
import BuildWeek.Epic_Energy_Service.utente.Utente;
import BuildWeek.Epic_Energy_Service.utente.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	JWTTools jTools;
	@Autowired
	UtenteService utenteService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");// estraggo il token dall'header
		if (header == null || !header.startsWith("Bearer "))
			throw new UnauthorizedException("Inserisci il token nell'autorization Header");
		String token = header.substring(7);
		System.out.println("Token: -> " + token);

		jTools.verificaToken(token);// verifico token
		String id = jTools.extractSubject(token);
		Utente utenteCorrente = utenteService.findById(UUID.fromString(id));// effettuo ricerca per id

		UsernamePasswordAuthenticationToken autorizzationToken = new UsernamePasswordAuthenticationToken(utenteCorrente,
				null, utenteCorrente.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(autorizzationToken);

		filterChain.doFilter(request, response);// posso andare avanti
		// se avro problemi l'errore sarà 401..

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		System.out.println(request.getServletPath());
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}
}
