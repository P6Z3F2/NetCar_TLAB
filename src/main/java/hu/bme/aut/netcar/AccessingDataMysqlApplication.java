package hu.bme.aut.netcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.geo.Point;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}
}

@RestController

class HelloController{
	@Autowired
	private ServiceRequestRepository serviceRequestRepository;

	@GetMapping("/hello")
	public String hello() {
		serviceRequestRepository.save(new ServiceRequest());
		return "Hello Heroku.";
	}

	@GetMapping("/pont")
	public @ResponseBody ServiceRequest pont(){
		ServiceRequest asd = new ServiceRequest();
		serviceRequestRepository.save(asd);
		return asd;
	}


}
