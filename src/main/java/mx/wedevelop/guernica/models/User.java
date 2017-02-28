package mx.wedevelop.guernica.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 26/02/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String userName;
    @Transient
    private String password;
    private String encodedPassword;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();

    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}
