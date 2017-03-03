package mx.wedevelop.guernica.models;

import javax.persistence.Entity;
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
}
