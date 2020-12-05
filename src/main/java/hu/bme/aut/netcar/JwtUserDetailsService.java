package hu.bme.aut.netcar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.geo.Coord;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ServiceRequestRepository serviceRequestRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setCredits(0);
		newUser.setNumberOfRatings(0);
		newUser.setRating(0.0);
		newUser.setValid(false);
		Car c = new Car();
		c.setUser(newUser);
		carRepository.save(c);
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}

	public Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}

	public Iterable<User> getAllInvalidUsers() {
		return userRepository.findByvalidFalse();
	}
	


	public Optional<User> getUserById(Integer UserId)
	{
		return userRepository.findById(UserId);
	}
	public Iterable<User> getAllValidUsers() {
		return userRepository.findByvalidTrue();
	}

	public User findUserByName(String username) {

		return userRepository.findByUsername(username);
	}

	public DefaultResponse updateUser( Integer id, User param){
		User user = userRepository.findById(id).get();
		user.setEmail(param.getEmail());
		user.setUsername(param.getUsername());
		user.setPassword(bcryptEncoder.encode(param.getPassword()));
		user.setPictureUrl(param.getPictureUrl());
		user.setCredits(param.getCredits());
		user.setValid(param.getValid());
		userRepository.save(user);

		return new DefaultResponse("USER_SUCCESSFUL_UPDATED");
	}

	public DefaultResponse updateUserValidation(Integer id){

		User user = userRepository.findById(id).get();
		user.setValid(true);
		userRepository.save(user);

		return new DefaultResponse("USER_SUCCESSFUL_UPDATED");

	}


	public
	DefaultResponse updateUserValidationNull(Integer id){
		User user = userRepository.findById(id).get();
		user.setValid(null);
		userRepository.save(user);

		return new DefaultResponse("USER_SUCCESSFUL_UPDATED");
	}


	public DefaultResponse deleteUser(Integer id){
		Optional<User> OptionalUser = userRepository.findById(id);
		User user = OptionalUser.get();
		userRepository.delete(user);
		return new DefaultResponse("deleted user with id: "+id);
	}

	public Iterable<Car> getAllCars() {
		return carRepository.findAll();
	}

	public Optional<Car> getCarById(@PathVariable(value = "id") Integer Id)
	{
		return carRepository.findById(Id);
	}

	public @ResponseBody
	DefaultResponse updateCar(Integer id,  String brand,
							   String model, String serial,
							   String pic, Boolean hasBoot,
							 Integer seats, Integer placeInBoot){
		Car car = carRepository.findById(id).get();
		car.setBrand(brand);
		car.setModel(model);
		car.setSerial(serial);
		car.setPic(pic);
		car.setHasBoot(hasBoot);
		car.setSeats(seats);
		car.setPlaceInBoot(placeInBoot);
		car.setFreeSeats(seats);
		car.setFreePlace(seats + placeInBoot);
		carRepository.save(car);

		return new DefaultResponse("Updated car with id: " +  id);
	}

	public Iterable<ServiceRequest> getAllRequests() {
		return serviceRequestRepository.findAll();
	}

	public Optional<ServiceRequest> getRequestById(Integer Id)
	{
		return serviceRequestRepository.findById(Id);
	}

	public Iterable<ServiceRequest> findRequestsByDriverID(Integer id) {
				return serviceRequestRepository.findAllByDriverID(id);
	}

	public Iterable<ServiceRequest> findRequestsByPassengerID(Integer id) {
		return serviceRequestRepository.findAllByPassengerID(id);
	}

	public DefaultResponse updateRequest(ServiceRequest sr)
	{
		Calendar c = Calendar.getInstance();

		switch(sr.getsRstatus()){

			case INPROGRESS:
				User driver = userRepository.findById(sr.getDriverID()).get();
				driver.setIsInProgress(true);
				userRepository.save(driver);
				sr.setStartTime(new Date(c.getTimeInMillis()));
				serviceRequestRepository.save(sr);
				break;

			case DENIED:
				sr.setFinishTime(new Date(c.getTimeInMillis()));
				serviceRequestRepository.save(sr);
				break;

			case FINISHED:
				User driver2 = userRepository.findById(sr.getDriverID()).get();
				driver2.setIsInProgress(false);
				userRepository.save(driver2);
				sr.setFinishTime(new Date(c.getTimeInMillis()));
				serviceRequestRepository.save(sr);
				break;

			default: break;

		}
		return new DefaultResponse("Successful update.");
	}


	public DefaultResponse addRequest(Integer driver, Integer passenger, Coord destination, Integer payment) {
		ServiceRequest newRequest = new ServiceRequest(driver,passenger,new Coord(destination.getX(),destination.getY()),payment);
		serviceRequestRepository.save(newRequest);
		String a = driver.toString()+passenger.toString()+payment.toString();
		return new DefaultResponse(a);
	}

	public DefaultResponse deleteRequests() {
		serviceRequestRepository.deleteAll();
		return new DefaultResponse("All requests deleted.");
	}
}
