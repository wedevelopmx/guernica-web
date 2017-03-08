package mx.wedevelop.guernica.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 26/02/17.
 */
@Entity
public class User extends AbstractDomain {

    private String userName;
    @Transient
    private String password;
    private String encodedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "role_id"),
    //     inverseJoinColumns = @joinColumn(name = "user_id"))
    private List<Role> roleList = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<WorkShift> workShiftList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Order> orderDetails = new ArrayList<>();

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
        product.setUser(this);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
        product.setUser(null);
    }

    public List<WorkShift> getWorkShiftList() {
        return workShiftList;
    }

    public void setWorkShiftList(List<WorkShift> workShiftList) {
        this.workShiftList = workShiftList;
    }

    public List<Order> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Order> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role role) {
        roleList.add(role);
    }
}
