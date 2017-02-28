package mx.wedevelop.guernica.models;

import javax.persistence.*;

/**
 * Created by colorado on 27/02/17.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String name;
    private String description;
    private double unitCost;

    @ManyToOne
    private User user;

    public Product() { }

    public Product(String name, String description, double unitCost, User user) {
        this.name = name;
        this.description = description;
        this.unitCost = unitCost;
        this.user = user;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
