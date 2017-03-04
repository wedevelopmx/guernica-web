package mx.wedevelop.guernica.services;

import mx.wedevelop.guernica.models.Order;
import mx.wedevelop.guernica.models.OrderDetail;
import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;

/**
 * Created by colorado on 4/03/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private UserServiceDao userServiceDao;

    private ProductService productService;

    private OrderService orderService;

    private User user;

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Before
    public void setup() {
        user = userServiceDao.findById(1);
        assert  user != null;
    }

    @Test
    public void testFindAll() {
        List<Order> orderList = orderService.findAll();

        assert orderList != null;
        assert orderList.size() == 3;
        assert orderList.get(0).getOrderDetails().size() == 4;
        assert orderList.get(1).getOrderDetails().size() == 4;
        assert orderList.get(1).getOrderDetails().size() == 4;
    }

    @Test
    public void testFindById() {
        Order order = orderService.findById(1);

        assert order != null;
        assert order.getId() == 1;
        assert order.getOrderDetails() != null;
        assert order.getOrderDetails().size() == 4;
    }

    @Test
    public void testSaveOrUpdate() {
        List<Product> productList = productService.findAll();
        Order order = mockOrder(user, productList);

        Order savedOrder = orderService.saveOrUpdate(order);

        assert savedOrder != null;
        assert savedOrder.getId() != null;
        assert savedOrder.getOrderDetails() != null;
        assert savedOrder.getOrderDetails().size() == productList.size();

        assert savedOrder.getUser() != null;
        assert savedOrder.getUser().getId() == order.getUser().getId();
    }

    @Test
    public void testDelete() {
        orderService.delete(1);
        List<Order> orderList = orderService.findAll();

        for(Order order : orderList) {
            assert order.getId() != 1;
        }

    }

    private Order mockOrder(User user, List<Product> productList) {
        Random rand = new Random();
        Order order = new Order();

        order.setUser(user);
        for(Product product : productList) {
            order.addOrderDetail(new OrderDetail(rand.nextInt(10) + 1, product));
        }

        return order;
    }
}
