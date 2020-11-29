package hu.bme.aut.netcar.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.bme.aut.netcar.model.User;


@Qualifier("user")
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String Username);

	//User findByName(String name);

	Iterable<User> findByvalidFalse();
	
}