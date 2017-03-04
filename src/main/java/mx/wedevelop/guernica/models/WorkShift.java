package mx.wedevelop.guernica.models;

import mx.wedevelop.guernica.enums.ShiftDay;
import mx.wedevelop.guernica.enums.ShiftType;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by colorado on 3/03/17.
 */
@Entity
public class WorkShift extends AbstractDomain {

    private ShiftType shiftType;
    private ShiftDay shiftDay;
    private String startHour;
    private String endHour;

    @ManyToOne
    private User user;

    public WorkShift() {

    }

    public WorkShift(ShiftType shiftType, ShiftDay shiftDay, String startHour, String endHour, User user) {
        this.shiftType = shiftType;
        this.shiftDay = shiftDay;
        this.startHour = startHour;
        this.endHour = endHour;
        this.user = user;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public void setShiftDay(ShiftDay shiftDay) {
        this.shiftDay = shiftDay;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public ShiftDay getShiftDay() {
        return shiftDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

}
