package co.com.ingeneo.prueba.repositories;

import co.com.ingeneo.prueba.entities.Delivery;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeliveryRepository extends CrudRepository<Delivery, UUID> {

}

