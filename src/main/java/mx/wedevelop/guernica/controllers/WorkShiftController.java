package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.enums.ShiftDay;
import mx.wedevelop.guernica.enums.ShiftType;
import mx.wedevelop.guernica.models.WorkShift;
import mx.wedevelop.guernica.services.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by colorado on 27/02/17.
 */
@Controller
public class WorkShiftController {

    private WorkShiftService workShiftService;

    @Autowired
    public void setWorkShiftService(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @RequestMapping("workshift")
    public String list(Model model) {
        model.addAttribute("workShiftList", workShiftService.findAll());
        return "workshift/list";
    }

    @RequestMapping("workshift/{id}")
    public String show( @PathVariable int id, Model model) {
        model.addAttribute("workShift", workShiftService.findById(id));
        return "workshift/show";
    }

    @RequestMapping("workshift/new")
    public String form(Model model) {
        model.addAttribute("shiftTypes", new ArrayList<ShiftType>(Arrays.asList(ShiftType.values())));
        model.addAttribute("shiftDays", new ArrayList<ShiftDay>(Arrays.asList(ShiftDay.values())));
        model.addAttribute("workShift", new WorkShift());
        return "workshift/new";
    }

    @RequestMapping("workshift/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("workShift", workShiftService.findById(id));
        return "workshift/new";
    }

    @RequestMapping(value = "workshift", method = RequestMethod.POST)
    public String create(@ModelAttribute WorkShift workShift) {
        WorkShift savedworkShift = workShiftService.saveOrUpdate(workShift);

        return "redirect:/workshift/" + savedworkShift.getId();
    }

    @RequestMapping("workshift/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        workShiftService.delete(id);
        return "redirect:/workshift";
    }
}
