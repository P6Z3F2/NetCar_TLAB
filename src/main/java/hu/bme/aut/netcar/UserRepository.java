package hu.bme.aut.netcar;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);

    Iterable<User> findByvalidFalse();

}
