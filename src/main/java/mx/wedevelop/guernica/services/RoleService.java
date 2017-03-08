package mx.wedevelop.guernica.services;

import mx.wedevelop.guernica.models.Role;

/**
 * Created by colorado on 8/03/17.
 */
public interface RoleService extends CRUDService<Role> {
    Role findByName(String name);
}
