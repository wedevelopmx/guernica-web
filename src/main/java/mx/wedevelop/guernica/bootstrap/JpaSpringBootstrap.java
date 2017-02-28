package mx.wedevelop.guernica.bootstrap;

import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.services.ProductService;
import mx.wedevelop.guernica.services.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by colorado on 27/02/17.
 */
@Component
public class JpaSpringBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserServiceDao userServiceDao;

    private ProductService productService;

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populateDatabase();
    }

    private void populateDatabase() {
        User user = userServiceDao.saveOrUpdate(new User("colorado", "pass1word"));

        productService.saveOrUpdate(new Product("Sabritas", "Papas sabritas", 12.2, user));
        productService.saveOrUpdate(new Product("Doritos", "Dorilocos", 13.2, user));
        productService.saveOrUpdate(new Product("Ruffles", "Sexy ruffles", 15.2, user));
        productService.saveOrUpdate(new Product("Chetos", "Orangies", 9.2, user));

    }
}
