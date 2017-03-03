package mx.wedevelop.guernica.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by colorado on 3/03/17.
 */
@Entity
public class WorkShift extends AbstractDomain {

    private String name;
    private Integer weekday;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    private User user;

    public WorkShift() {

    }

    public WorkShift(String name, Integer weekday, Date startDate, Date endDate, User user) {
        this.name = name;
        this.weekday = weekday;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
