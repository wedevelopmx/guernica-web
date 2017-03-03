package mx.wedevelop.guernica.bootstrap;

import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.models.WorkShift;
import mx.wedevelop.guernica.services.ProductService;
import mx.wedevelop.guernica.services.UserServiceDao;
import mx.wedevelop.guernica.services.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by colorado on 27/02/17.
 */
@Component
public class JpaSpringBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserServiceDao userServiceDao;

    private ProductService productService;

    private WorkShiftService workShiftService;

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setWorkShiftService(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populateDatabase();
    }

    private void populateDatabase() {
        User user = userServiceDao.saveOrUpdate(new User("colorado", "pass1word"));
        populateProducts(user);
        populateWorkShift(user);
    }

    private void populateProducts(User user) {
        productService.saveOrUpdate(new Product("Sabritas", "Papas sabritas", 12.2, user));
        productService.saveOrUpdate(new Product("Doritos", "Dorilocos", 13.2, user));
        productService.saveOrUpdate(new Product("Ruffles", "Sexy ruffles", 15.2, user));
        productService.saveOrUpdate(new Product("Chetos", "Orangies", 9.2, user));

    }

    private void populateWorkShift(User user) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date startHour = null;
        Date endHour = null;

        try {
            startHour = df.parse("08:00:00");
            endHour = df.parse("20:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        workShiftService.saveOrUpdate(new WorkShift("allDay", 1, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift("allDay", 2, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift("allDay", 3, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift("allDay", 4, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift("allDay", 5, startHour, endHour, user));
        workShiftService.saveOrUpdate(new WorkShift("allDay", 6, startHour, endHour, user));
    }
}
