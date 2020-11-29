package hu.bme.aut.netcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller	// This means that this class is a Controller
@CrossOrigin
@RequestMapping
public class MainController {
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping(path="/getAllUsers")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userDetailsService.getAllUsers();
	}

	@GetMapping(path="/getAllInvalidUsers")
	public @ResponseBody Iterable<User> getAllInvalidUsers() {
		return userDetailsService.getAllInvalidUsers();
	}

	@GetMapping(path = "/getUser/{id}")
	public  @ResponseBody  Optional<User> getUserById(@PathVariable(value = "id") Integer UserId)
	{
		return userDetailsService.getUserById(UserId);
	}

	@GetMapping(path = "/getUserByName/{username}")
	public @ResponseBody User findUserByName(@PathVariable String username) {

		return userDetailsService.findUserByName(username);
	}

	@PutMapping(value = "/updateUser/{id}", produces = "application/json")
	public @ResponseBody
	DefaultResponse updateUser(@PathVariable(value = "id") Integer id,
							   @RequestBody User param){
		return userDetailsService.updateUser(id,param);

	}

	@PutMapping(value = "/updateUserValidation/{id}", produces = "application/json")
	public @ResponseBody
	DefaultResponse updateUserValidation(@PathVariable(value = "id") Integer id){
		return userDetailsService.updateUserValidation(id);
	}

	@PutMapping(value = "/updateUserValidationNull/{id}", produces = "application/json")
	public @ResponseBody
	DefaultResponse updateUserValidationNull(@PathVariable(value = "id") Integer id){
		return userDetailsService.updateUserValidationNull(id);
	}


	@RequestMapping(value="/deleteUser/{id}", method={RequestMethod.DELETE, RequestMethod.GET})
	public @ResponseBody
	DefaultResponse deleteUser(@PathVariable(value = "id") Integer id){
		return userDetailsService.deleteUser(id);
	}

	// ----------------------------------------------------------------------------- Car

	//GET ALL CARS
	@GetMapping(path="/getAllCars")
	public @ResponseBody Iterable<Car> getAllCars() {
		// This returns a JSON or XML with the users
		return userDetailsService.getAllCars();
	}

	// GET CAR
	@GetMapping(path = "/getCar/{id}")
	public  @ResponseBody  Optional<Car> getCarById(@PathVariable(value = "id") Integer Id)
	{
		return userDetailsService.getCarById(Id);
	}
	//UPDATE CAR
	@PutMapping(path = "/getUser/{id}/updateCar")
	public @ResponseBody
	DefaultResponse updateCar(@PathVariable(value = "id") Integer id, @RequestParam String brand,
							  @RequestParam String model, @RequestParam String serial,
							  @RequestParam String pic, @RequestParam Boolean hasBoot,
							  @RequestParam Integer seats, @RequestParam Integer placeInBoot){
		return userDetailsService.updateCar(id,brand,model,serial,pic,hasBoot,seats,placeInBoot);
	}

	//DELETE CAR
	@PutMapping(path = "/getUser/{id}/deleteCar")
	public @ResponseBody
	DefaultResponse deleteCar(@PathVariable(value = "id") Integer id){
		return  updateCar(id,null,null,null, null, false, null, null);
	}
}