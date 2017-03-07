package mx.wedevelop.guernica.services.security;

import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.services.impl.UserServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by colorado on 6/03/17.
 */
@Service(value = "userDetailsSetvice")
public class UserDetailServiceImpl implements UserDetailsService {

    private UserServiceDaoImpl userServiceDao;

    private Converter<User, UserDetails> userUserDetailsConverter;

    @Autowired
    public void setUserServiceDao(UserServiceDaoImpl userServiceDao) {
        this.userServiceDao = userServiceDao;
    }


    @Autowired
    @Qualifier(value = "userToUserDetailsConverter")
    public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userServiceDao.findByUserName(userName));
    }
}
