package hu.bme.aut.netcar;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
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
		newUser.setValid(null);
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

	public Iterable<User> getAllValidNull(){
		return userRepository.findByValidIsNull();
	}

	public User findUserByName(String username) {

		return userRepository.findByUsername(username);
	}

	public DefaultResponse updateUser( Integer id, User param){
		User user = userRepository.findById(id).get();
		user.setEmail(param.getEmail());
		user.setUsername(param.getUsername());
		user.setPassword(param.getPassword());
		user.setPicture(param.getPicture());
		user.setCredits(param.getCredits());
		user.setValid(param.getValid());
		user.setRatings(param.getRatings());
		user.setVisible(param.getVisible());
		user.setLocation(param.getLocation());
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

	public DefaultResponse updateCar(Integer id, Car car){
		Car newCar = carRepository.findById(id).get();
		newCar.setBrand(car.getBrand());
		newCar.setModel(car.getModel());
		newCar.setSerial(car.getSerial());
		newCar.setPic(car.getPic());
		newCar.setHasBoot(car.getHasBoot());
		newCar.setSeats(car.getSeats());
		newCar.setPlaceInBoot(car.getPlaceInBoot());
		newCar.setFreeSeats(car.getSeats());
		newCar.setFreePlace(car.getSeats() + car.getPlaceInBoot());
		carRepository.save(newCar);

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
		ServiceRequest newer = serviceRequestRepository.findById(sr.getSrid()).get();
		newer.setsRstatus(sr.getsRstatus());

		User passenger = userRepository.findById(newer.getPassengerID()).get();
		User driver = userRepository.findById(newer.getDriverID()).get();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		Calendar c = Calendar.getInstance();

		switch(newer.getsRstatus()){
			case INPROGRESS:
				driver.setIsInProgress(true);
				userRepository.save(driver);

				passenger.setIsInProgress(true);
				userRepository.save(passenger);

				Iterable<ServiceRequest> requests = serviceRequestRepository.findAllByDriverID(newer.getDriverID());
				for (ServiceRequest r : requests) {
					if (!r.getSrid().equals(newer.getSrid()) && r.getsRstatus() == SRstatus.PENDING) {
						r.setsRstatus(SRstatus.DENIED);
					}
				}

				newer.setStartTime(sdf.format(new Date(c.getTimeInMillis())));
				break;

			case DENIED:
				newer.setFinishTime(sdf.format(new Date(c.getTimeInMillis())));
				break;

			case FINISHED:
				driver.setIsInProgress(false);
				driver.setCredits(driver.getCredits() + newer.getPayment());
				userRepository.save(driver);

				passenger.setIsInProgress(false);
				passenger.setCredits(passenger.getCredits() - newer.getPayment());
				userRepository.save(passenger);

				newer.setFinishTime(sdf.format(new Date(c.getTimeInMillis())));
				break;

			default: break;

		}
		serviceRequestRepository.save(newer);
		return new DefaultResponse("Successful update.");
	}


	public DefaultResponse addRequest(Integer driver, Integer passenger, Coord destination, Integer payment) {
		ServiceRequest newRequest = new ServiceRequest(driver,passenger,new Coord(destination.getX(),destination.getY()),payment);
		serviceRequestRepository.save(newRequest);
		return new DefaultResponse("Creating request was successful.");
	}

	public DefaultResponse deleteRequests() {
		serviceRequestRepository.deleteAll();
		return new DefaultResponse("All requests deleted.");
	}

	public String getUserPictureById(Integer userId) {
		User user = userRepository.findById(userId).get();
		return user.getPicture();
	}

	public String getUsernameById(Integer userId) {
		User user = userRepository.findById(userId).get();
		return user.getUsername();
	}

	public DefaultResponse deleteCar(Integer id) {
		User user = userRepository.findById(id).get();
		Car c = new Car();
		c.setUser(user);
		c.setId(id);
		carRepository.save(c);
		return new DefaultResponse("Car has been deleted.");
	}

	public Optional<ServiceRequest> findActiveRequest(Integer id) {
		Iterable<ServiceRequest> srD = serviceRequestRepository.findAllByDriverID(id);
		for (ServiceRequest sr : srD) {
			if (sr.getsRstatus() == SRstatus.INPROGRESS) {
				return serviceRequestRepository.findById(sr.getSrid());
			}
		}

		Iterable<ServiceRequest> srP = serviceRequestRepository.findAllByPassengerID(id);
		for (ServiceRequest sr : srP) {
			if (sr.getsRstatus() == SRstatus.INPROGRESS) {
				return serviceRequestRepository.findById(sr.getSrid());
			}
		}

		return Optional.empty();
	}
}
