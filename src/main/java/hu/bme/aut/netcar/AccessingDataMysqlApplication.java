package hu.bme.aut.netcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}
}

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
class HelloController{
	@GetMapping("/hello")
	public String hello() {
		return "Hello Heroku.";
	}
}
