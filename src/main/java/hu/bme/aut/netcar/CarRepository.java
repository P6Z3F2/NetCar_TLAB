package hu.bme.aut.netcar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Qualifier("car")
@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {



}
