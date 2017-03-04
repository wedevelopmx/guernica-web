package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.enums.ShiftDay;
import mx.wedevelop.guernica.enums.ShiftType;
import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.models.WorkShift;
import mx.wedevelop.guernica.services.WorkShiftService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

//import static org.hamcrest.Matchers.instanceOf;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.hasProperty;
//import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by colorado on 27/02/17.
 */
public class WorkShiftControllerTest {
    @InjectMocks
    private WorkShiftController workShiftController;

    @Mock
    private WorkShiftService workShiftService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //Initialize mocks
        MockitoAnnotations.initMocks(this);
        //Setup
        mockMvc = MockMvcBuilders.standaloneSetup(workShiftController).build();
    }

    @Test
    public void testList() throws Exception {
        User user = new User();
        List<WorkShift> productList = new ArrayList<>();

        productList.add(new WorkShift(ShiftType.MORNING, ShiftDay.MONDAY, "8:00 AM", "9:00 PM", user));
        productList.add(new WorkShift(ShiftType.MORNING, ShiftDay.TUESDAY, "8:00 AM", "9:00 PM", user));
        productList.add(new WorkShift(ShiftType.MORNING, ShiftDay.WEDNESDAY, "8:00 AM", "9:00 PM", user));

        Mockito.when(workShiftService.findAll()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/workshift"))
                .andExpect(status().isOk())
                .andExpect(view().name("workshift/list"))
                .andExpect(model().attribute("workShiftList", hasSize(3)));
    }

    @Test
    public void testShow() throws Exception {
        User user = new User();
        WorkShift workShift = new WorkShift(ShiftType.MORNING, ShiftDay.MONDAY, "8:00 AM", "9:00 PM", user);
        workShift.setId(1);

        Mockito.when(workShiftService.findById(workShift.getId())).thenReturn(workShift);

        mockMvc.perform(MockMvcRequestBuilders.get("/workshift/" + workShift.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("workshift/show"))
                .andExpect(model().attribute("workShift", instanceOf(WorkShift.class)));
    }

    @Test
    public void testForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/workshift/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("workshift/new"))
                .andExpect(model().attribute("workShift", instanceOf(WorkShift.class)));
    }

    @Test
    public void testEdit() throws Exception {
        User user = new User();
        WorkShift workShift = new WorkShift(ShiftType.MORNING, ShiftDay.MONDAY, "8:00 AM", "9:00 PM", user);
        workShift.setId(1);

        Mockito.when(workShiftService.findById(workShift.getId())).thenReturn(workShift);

        mockMvc.perform(MockMvcRequestBuilders.get("/workshift/" + workShift.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("workshift/new"))
                .andExpect(model().attribute("workShift", instanceOf(WorkShift.class)));
    }

    @Test
    public void testCreate() throws Exception {
        User user = new User();
        WorkShift workShift = new WorkShift(ShiftType.MORNING, ShiftDay.MONDAY, "8:00 AM", "9:00 PM", user);
        workShift.setId(1);

        when(workShiftService.saveOrUpdate(any()))
                .thenReturn(workShift);

        mockMvc.perform(MockMvcRequestBuilders.post("/workshift")
                .param("id", workShift.getId() + "")
                .param("shiftType", workShift.getShiftType() + "")
                .param("shiftDay", workShift.getShiftDay() + "")
                .param("startHour", workShift.getStartHour())
                .param("endHour", workShift.getEndHour()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/workshift/" + workShift.getId()))
                .andExpect(model().attribute("workShift", instanceOf(WorkShift.class)))
                .andExpect(model().attribute("workShift", hasProperty("id", is(workShift.getId()))))
                .andExpect(model().attribute("workShift", hasProperty("shiftType", is(workShift.getShiftType()))))
                .andExpect(model().attribute("workShift", hasProperty("shiftDay", is(workShift.getShiftDay()))))
                .andExpect(model().attribute("workShift", hasProperty("startHour", is(workShift.getStartHour()))))
                .andExpect(model().attribute("workShift", hasProperty("endHour", is(workShift.getEndHour()))));

        ArgumentCaptor<WorkShift> captor = ArgumentCaptor.forClass(WorkShift.class);
        verify(workShiftService).saveOrUpdate(captor.capture());

        assertEquals(workShift.getId(), captor.getValue().getId());
        assertEquals(workShift.getShiftType(), captor.getValue().getShiftType());
        assertEquals(workShift.getShiftDay(), captor.getValue().getShiftDay());
        assertEquals(workShift.getStartHour(), captor.getValue().getStartHour());
        assertEquals(workShift.getEndHour(), captor.getValue().getEndHour());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/workshift/" + id + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/workshift"));

        verify(workShiftService, times(1)).delete(id);
    }

}
