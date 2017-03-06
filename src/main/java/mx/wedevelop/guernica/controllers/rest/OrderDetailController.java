package mx.wedevelop.guernica.controllers.rest;

import mx.wedevelop.guernica.models.Order;
import mx.wedevelop.guernica.models.OrderDetail;
import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.services.OrderService;
import mx.wedevelop.guernica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by colorado on 5/03/17.
 */
@RestController
public class OrderDetailController {

    private ProductService productService;

    private OrderService orderService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/catalog")
    public @ResponseBody  List<Product> findProductList() {
        return productService.findAll();
    }

    @RequestMapping(value = "/create-order", method = RequestMethod.POST)
    public @ResponseBody Order addOrder(@RequestBody Order order) {
        order.attachOrderDetails();
        Order savedOrder = orderService.saveOrUpdate(order);
        return savedOrder;
    }

    @RequestMapping("/find-order/{id}")
    public @ResponseBody Order readOrder(@PathVariable Integer id) {
        return orderService.findById(id);
    }
}
