package mx.wedevelop.guernica.services.repositories;

import mx.wedevelop.guernica.models.Role;
import mx.wedevelop.guernica.repositories.RolesRepository;
import mx.wedevelop.guernica.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 8/03/17.
 */
@Service
public class RolesServiceImpl implements RoleService {

    private RolesRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = new ArrayList<>();
        rolesRepository.findAll().forEach(roleList::add);
        return roleList;
    }

    @Override
    public Role findById(int id) {
        return rolesRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role object) {
        return rolesRepository.save(object);
    }

    @Override
    public void delete(int id) {
        rolesRepository.delete(id);
    }

    @Override
    public Role findByName(String name) {
        return rolesRepository.findByName(name);
    }
}
