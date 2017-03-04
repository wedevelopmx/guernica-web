package mx.wedevelop.guernica.services;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import mx.wedevelop.guernica.enums.ShiftDay;
import mx.wedevelop.guernica.enums.ShiftType;
import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.models.WorkShift;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by colorado on 3/03/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WorkShiftServiceImplTest {
    private WorkShiftService workShiftService;

    private UserServiceDao userServiceDao;

    private User user = new User();

    @Autowired
    public void setWorkShiftService(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @Before
    public void setup() {
        user = userServiceDao.findById(1);
        assert  user != null;
    }

    @Test
    public void testFindAll() {
        List<WorkShift> workShiftList = workShiftService.findAll();

        System.out.println("Size: " + workShiftList.size());
        assert workShiftList.size() == 6;
    }

    @Test
    public void testFindById() {
        WorkShift workShift = workShiftService.findById(1);

        assert workShift != null;
        assert workShift.getId() == 1;
    }

    @Test
    public void testSaveOrUpdate() {
        WorkShift workShift = new WorkShift(ShiftType.MORNIGN, ShiftDay.SATURDAY, "8:00 AM", "8:00 PM", user);

        WorkShift savedWorkShift = workShiftService.saveOrUpdate(workShift);

        assert savedWorkShift != null;
        assert savedWorkShift.getId() != null;
        assert savedWorkShift.getShiftDay() == workShift.getShiftDay();
        assert savedWorkShift.getShiftType() == workShift.getShiftType();
        assert savedWorkShift.getStartHour().equalsIgnoreCase(workShift.getStartHour());
        assert savedWorkShift.getEndHour().equalsIgnoreCase(workShift.getEndHour());

        assert savedWorkShift.getUser() != null;
        assert savedWorkShift.getUser().getId() == user.getId();
    }

    @Test
    public void testDelete() {
        workShiftService.delete(1);
        List<WorkShift> workShiftList = workShiftService.findAll();

        for(WorkShift workShift : workShiftList) {
            assert workShift.getId() != 1;
        }
    }
}
