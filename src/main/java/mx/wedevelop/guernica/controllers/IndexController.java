package mx.wedevelop.guernica.controllers;

import mx.wedevelop.guernica.models.User;
import mx.wedevelop.guernica.services.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by colorado on 26/02/17.
 */
@Controller
public class IndexController {

    private UserServiceDao userServiceDao;

    @Autowired
    public void setUserServiceDao(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String login(@ModelAttribute("user")  User user, Model model) {
        User foundUser = userServiceDao.findByUserName(user.getUserName());

        if(foundUser != null) {
            System.out.println("Found : " + foundUser.getUserName());
            return "home";
        } else {
            model.addAttribute("errorMessage", "Could not find username " + user.getUserName());
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("user") User user, Model model) {
        User savedUser = userServiceDao.saveOrUpdate(user);

        System.out.println("Saved: " + savedUser.getUserName());
        return "home";
    }
}
