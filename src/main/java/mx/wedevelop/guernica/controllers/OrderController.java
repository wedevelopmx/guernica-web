package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.models.Order;
import mx.wedevelop.guernica.models.OrderDetail;
import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.services.OrderService;
import mx.wedevelop.guernica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by colorado on 4/03/17.
 */
@Controller
public class OrderController {

    private OrderService orderService;
    private ProductService productService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("order")
    public String list(Model model) {
        model.addAttribute("orderList", orderService.findAll());
        return "order/list";
    }

    @RequestMapping("order/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/show";
    }

    private Order createEmptyOrder(List<Product> productList) {
        Order order = new Order();
        for(Product product : productList) {
            order.addOrderDetail(new OrderDetail(0, product));
        }
        return order;
    }

    @RequestMapping("order/new")
    public String form(Model model) {
        model.addAttribute("order", createEmptyOrder(productService.findAll()));
        return "order/new";
    }

    @RequestMapping("order/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("productList", productService.findAll());
        model.addAttribute("order", orderService.findById(id));
        return "order/new";
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public String create(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "orderDetail.product.id[]") String[] products,
            @RequestParam(value = "orderDetail.quantity[]") String[] quantities) {




        Order savedOrder = orderService.saveOrUpdate(new Order());

        return "redirect:/order/" + savedOrder.getId();
    }

    @RequestMapping("order/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        orderService.delete(id);
        return "redirect:/order";
    }
}
