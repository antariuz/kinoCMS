package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.User;
import avadamedia.kinocms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService service;

    @RequestMapping({"/", ""})
    public String userPage(){
        return "admin/users";
    }

//    @GetMapping("/all")
//    public ModelAndView users() {
//        return new ModelAndView("admin/users", "users", service.getAllUsers());
//    }
//
//    @GetMapping("/add")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "admin/add-user";
//    }
//
//    @PostMapping("/add")
//    public String addUser(User user) {
//        service.createUser(user);
//        return "redirect:admin/users";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//
//    @GetMapping("/update/{id}")
//    public ModelAndView changeUser(@PathVariable("id") Integer id) {
//        return new ModelAndView("admin/change-user", "user", service.getUserById(id));
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteProducer(@PathVariable("id") Integer id) {
//        service.deleteUserById(id);
//        return "redirect:/users/all";
//    }
//
//    @PutMapping("/all")
//    public String changeUser(User user) {
//        service.changeUser(user);
//        return "redirect:/users/all";
//    }
}