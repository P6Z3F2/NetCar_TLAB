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

	@GetMapping(path="/getAllValidNull")
	public @ResponseBody Iterable<User> getAllValidNull() {

		return userDetailsService.getAllValidNull();
	}


	@GetMapping(path="/getAllValidUsers")
	public @ResponseBody Iterable<User> getAllValidUsers() {

		return userDetailsService.getAllValidUsers();
	}

	@GetMapping(path = "/getUser/{id}")
	public  @ResponseBody  Optional<User> getUserById(@PathVariable(value = "id") Integer UserId)
	{
		return userDetailsService.getUserById(UserId);
	}

	@GetMapping(path = "/getUsername/{id}")
	public  @ResponseBody  String getUsernameById(@PathVariable(value = "id") Integer UserId)
	{
		return userDetailsService.getUsernameById(UserId);
	}

	@GetMapping(path = "/getUserPicture/{id}")
	public  @ResponseBody  String getUserPicture(@PathVariable(value = "id") Integer UserId)
	{
		return userDetailsService.getUserPictureById(UserId);
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
	/*
	//UPDATE CAR
	@PutMapping(path = "/getUser/{id}/updateCar")
	public @ResponseBody
	DefaultResponse updateCar(@PathVariable(value = "id") Integer id, @RequestParam String brand,
							  @RequestParam String model, @RequestParam String serial,
							  @RequestParam String pic, @RequestParam Boolean hasBoot,
							  @RequestParam Integer seats, @RequestParam Integer placeInBoot){
		return userDetailsService.updateCar(id,brand,model,serial,pic,hasBoot,seats,placeInBoot);
	}*/

	@PutMapping(path = "/getUser/{id}/updateCar")
	public @ResponseBody
	DefaultResponse updateCar(@PathVariable(value = "id") Integer id, @RequestBody Car car)
	{
		return userDetailsService.updateCar(id, car);
	}

	//DELETE CAR
	@PutMapping(path = "/getUser/{id}/deleteCar")
	public @ResponseBody
	DefaultResponse deleteCar(@PathVariable(value = "id") Integer id) {
		return  userDetailsService.deleteCar(id);
	}

	// REQUEST --------------------------------------------------------------


	@GetMapping(path = "/getAllRequests")
	public @ResponseBody Iterable<ServiceRequest> getAllRequests()
	{
		return userDetailsService.getAllRequests();
	}

	@GetMapping(path = "/getRequest/{id}")
	public @ResponseBody Optional<ServiceRequest> getRequest(@PathVariable(value = "id")Integer id)
	{
		return userDetailsService.getRequestById(id);
	}

	@GetMapping(path = "/getRequestsByDriver/{id}")
	public @ResponseBody Iterable<ServiceRequest> getRequestsByDriver(@PathVariable(value = "id")Integer id)
	{
		return userDetailsService.findRequestsByDriverID(id);
	}

	@GetMapping(path = "/getRequestsByPassenger/{id}")
	public @ResponseBody Iterable<ServiceRequest> getRequestsByPassenger(@PathVariable(value = "id")Integer id)
	{
		return userDetailsService.findRequestsByPassengerID(id);
	}

	@GetMapping(path = "/getActiveRequest/{id}")
	public @ResponseBody Optional<ServiceRequest> getActiveRequest(@PathVariable(value = "id")Integer id)
	{
		return userDetailsService.findActiveRequest(id);
	}

	@PutMapping(path = "/updateRequest")
	public @ResponseBody  DefaultResponse updateRequest(@RequestBody ServiceRequest newer)
	{
		return userDetailsService.updateRequest(newer);
	}

	@PostMapping(path = "/addRequest")


	//public @ResponseBody  DefaultResponse addRequest(Integer driver, Integer passenger, Coord destination, Integer payment)
	public @ResponseBody  DefaultResponse addRequest(@RequestBody ServiceRequest sr)
	{
		return userDetailsService.addRequest(sr.getDriverID(), sr.getPassengerID(),sr.getDestinationPos(),sr.getPayment());
	}

 	@PostMapping(path = "/deleteRequests")
	public @ResponseBody DefaultResponse deleteRequests(){
		return userDetailsService.deleteRequests();
	}

}



