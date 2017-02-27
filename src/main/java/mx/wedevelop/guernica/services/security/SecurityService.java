package mx.wedevelop.guernica.services.security;

/**
 * Created by colorado on 27/02/17.
 */
public interface SecurityService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
