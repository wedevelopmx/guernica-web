package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.services.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by colorado on 8/03/17.
 */
@Controller
public class UserController {

    private UserServiceDao userServiceDao;

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @RequestMapping("/user")
    public String userAdminDashboard(Model model) {
        model.addAttribute("userList", userServiceDao.findAll());
        return "user/list";
    }

    @RequestMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable Integer id, Model model) {
        userServiceDao.delete(id);
        return "redirect:/user";
    }
}
