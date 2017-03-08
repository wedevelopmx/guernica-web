package mx.wedevelop.guernica.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by colorado on 8/03/17.
 */
@Entity
public class Role extends AbstractDomain {
    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "role_id"),
    //     inverseJoinColumns = @joinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    private Role() { }

    public Role(String roleName, String description) {
        this.name = roleName;
        this.description = description;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

