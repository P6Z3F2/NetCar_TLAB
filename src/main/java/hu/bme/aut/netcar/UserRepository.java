package hu.bme.aut.netcar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Qualifier("user")
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String Username);

    //User findByName(String name);

    Iterable<User> findByvalidFalse();
    Iterable<User> findByvalidTrue();

}
