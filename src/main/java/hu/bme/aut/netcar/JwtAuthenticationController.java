package hu.bme.aut.netcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public DefaultResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		}

		catch (BadCredentialsException e) {return new DefaultResponse("Wrong username or password");}
		catch (DisabledException e) {return new DefaultResponse("User disabled.");}
		final UserDetails userDetails;

		try {
			userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		} catch (UsernameNotFoundException e){
			return new DefaultResponse("Username not found");
		}

		final String token = jwtTokenUtil.generateToken(userDetails);

		User user = userDetailsService.findUserByName(authenticationRequest.getUsername());
		//return ResponseEntity.ok(new JwtResponse(token));
		String message = token + " " + user.getId();
		return new DefaultResponse(message);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		//username és pw

		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}