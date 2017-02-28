package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.models.Product;
import mx.wedevelop.guernica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by colorado on 27/02/17.
 */
@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/product")
    public String list(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "product/list";
    }

    @RequestMapping("/product/{id}")
    public String show( @PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/show";
    }

    @RequestMapping("/product/new")
    public String form(Model model) {
        model.addAttribute("product", new Product());
        return "product/new";
    }

    @RequestMapping("/product/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/new";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String create(@ModelAttribute Product product) {
        Product savedProduct = productService.saveOrUpdate(product);

        return "redirect:/product/" + savedProduct.getId();
    }

    @RequestMapping("/product/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        productService.delete(id);
        return "redirect:/product";
    }
}
