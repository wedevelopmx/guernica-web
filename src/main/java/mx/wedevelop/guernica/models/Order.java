package mx.wedevelop.guernica.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 4/03/17.
 */
@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomain {

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.setOrder(null);
    }
}
