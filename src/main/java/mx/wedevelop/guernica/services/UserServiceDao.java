package mx.wedevelop.guernica.services;

import mx.wedevelop.guernica.models.User;

import java.util.List;

/**
 * Created by colorado on 26/02/17.
 */
public interface UserServiceDao {
    List<User> findAll();
    User findById(int id);
    User findByUserName(String userName);
    User saveOrUpdate(User user);
}
