package hu.bme.aut.netcar;

import org.springframework.data.repository.CrudRepository;

public interface ServiceRequestRepository extends CrudRepository
                                            <ServiceRequest, Integer> {


    Iterable<ServiceRequest> findAllByDriverID(Integer id);
    Iterable<ServiceRequest> findAllByPassengerID(Integer id);


}
