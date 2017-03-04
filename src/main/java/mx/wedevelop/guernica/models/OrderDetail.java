package mx.wedevelop.guernica.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by colorado on 4/03/17.
 */
@Entity
public class OrderDetail extends AbstractDomain {

    private Integer quantity;

    @OneToOne
    private Product product;

    @ManyToOne
    private Order order;

    public OrderDetail() {

    }

    public OrderDetail(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
