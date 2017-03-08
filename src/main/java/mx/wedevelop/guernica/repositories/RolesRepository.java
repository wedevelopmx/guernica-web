package mx.wedevelop.guernica.repositories;

import mx.wedevelop.guernica.models.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by colorado on 8/03/17.
 */
public interface RolesRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
