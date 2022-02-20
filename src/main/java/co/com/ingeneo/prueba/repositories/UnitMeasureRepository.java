package co.com.ingeneo.prueba.repositories;

import co.com.ingeneo.prueba.entities.UnitMeasure;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UnitMeasureRepository extends CrudRepository<UnitMeasure, UUID> {

}

