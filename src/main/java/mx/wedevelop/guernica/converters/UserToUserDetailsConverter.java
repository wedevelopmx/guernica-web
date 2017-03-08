package mx.wedevelop.guernica.converters;

import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.services.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by colorado on 6/03/17.
 */
@Component
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(user.getUserName());
        userDetails.setPassword(user.getEncodedPassword());
        userDetails.setEnabled(true);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoleList().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
