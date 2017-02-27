package mx.wedevelop.guernica.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by colorado on 27/02/17.
 */
@Configuration
public class CommonBeanConfig {

    @Bean
    public StrongPasswordEncryptor strongEntryptor() {
        return new StrongPasswordEncryptor();
    }
}
