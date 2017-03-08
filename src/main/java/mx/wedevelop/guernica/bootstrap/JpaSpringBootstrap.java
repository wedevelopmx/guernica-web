package mx.wedevelop.guernica.bootstrap;

import mx.wedevelop.guernica.enums.ShiftDay;
import mx.wedevelop.guernica.enums.ShiftType;
import mx.wedevelop.guernica.models.*;
import mx.wedevelop.guernica.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by colorado on 27/02/17.
 */
@Component
public class JpaSpringBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserServiceDao userServiceDao;

    private RoleService roleService;

    private ProductService productService;

    private WorkShiftService workShiftService;

    private OrderService orderService;

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setWorkShiftService(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populateDatabase();
    }

    private void populateDatabase() {
        populateRoles();
        User user = createSuperUser("colorado", "pass1word");
        populateProducts(user);
        populateWorkShift(user);
        populateOrder(user);
    }

    private User createSuperUser(String name, String password) {
        User user = new User(name, password);
        user.addRole(roleService.findById(1));
        user.addRole(roleService.findById(2));
        return userServiceDao.saveOrUpdate(user);
    }

    private void populateProducts(User user) {
        productService.saveOrUpdate(new Product("Sabritas", "Papas sabritas", 12.2, user));
        productService.saveOrUpdate(new Product("Doritos", "Dorilocos", 13.2, user));
        productService.saveOrUpdate(new Product("Ruffles", "Sexy ruffles", 15.2, user));
        productService.saveOrUpdate(new Product("Chetos", "Orangies", 9.2, user));

    }

    private void populateWorkShift(User user) {
        String startHour = "08:00 AM";
        String endHour = "08:00 PM";

        workShiftService.saveOrUpdate(new WorkShift(ShiftType.FULL, ShiftDay.SUNDAY, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift(ShiftType.FULL, ShiftDay.MONDAY, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift(ShiftType.FULL, ShiftDay.TUESDAY, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift(ShiftType.FULL, ShiftDay.WEDNESDAY, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift(ShiftType.FULL, ShiftDay.THURSDAY, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift(ShiftType.FULL, ShiftDay.FRIDAY, startHour, endHour, user));
    }

    private void populateOrder(User user) {
        List<Product> productList = productService.findAll();

        orderService.saveOrUpdate(mockOrder(user, productList));
        orderService.saveOrUpdate(mockOrder(user, productList));
        orderService.saveOrUpdate(mockOrder(user, productList));
    }

    private Order mockOrder(User user, List<Product> productList) {
        Random rand = new Random();
        Order order = new Order();
        Double total = 0.0;

        order.setUser(user);
        for(Product product : productList) {
            OrderDetail orderDetail = new OrderDetail(rand.nextInt(10) + 1, product);
            order.addOrderDetail(orderDetail);
            total += orderDetail.getTotal();
        }

        order.setTotal(total);

        return order;
    }

    private void populateRoles() {
        roleService.saveOrUpdate(new Role("USER", "Common user"));
        roleService.saveOrUpdate(new Role("ADMIN", "Super user"));
    }
}
