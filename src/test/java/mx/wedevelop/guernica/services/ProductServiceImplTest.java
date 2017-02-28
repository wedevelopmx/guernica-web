package mx.wedevelop.guernica.services;

import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by colorado on 27/02/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    private ProductService productService;

    private UserServiceDao userServiceDao;

    private User user = new User();

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @Before
    public void setup() {
        user = userServiceDao.findById(1);
        assert user.getId() != null;
    }

    @Test
    public void testFindAll() {
        List<Product> productList = productService.findAll();

        assert productList != null;
        assert productList.size() == 4;
    }

    @Test
    public void testFindById() {
        Product product = productService.findById(1);

        assert product != null;
        assert product.getId() == 1;
    }

    @Test
    public void testSaveOrUpdate() {
        Product product = new Product();
        product.setName("Sabritas");
        product.setDescription("Papas sabritas");
        product.setUnitCost(12.6f);
        product.setUser(user);

        Product savedProduct = productService.saveOrUpdate(product);

        assert savedProduct.getId() != null;
        assert  savedProduct.getName() == product.getName();
        assert  savedProduct.getDescription() == product.getDescription();
        assert  savedProduct.getUnitCost() == product.getUnitCost();

        assert savedProduct.getUser().getId() == user.getId();
        assert savedProduct.getUser().getUserName() == user.getUserName();
        assert savedProduct.getUser().getEncodedPassword() == user.getEncodedPassword();
    }

    @Test
    public void testDelete() {
        productService.delete(1);
        List<Product> productList = productService.findAll();

        for(Product product : productList) {
            assert product.getId() != 1;
        }
    }
}
