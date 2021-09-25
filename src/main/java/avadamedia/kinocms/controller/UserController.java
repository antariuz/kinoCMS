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

    //    Show all Users
    @GetMapping({"/", ""})
    public ModelAndView users() {
        return new ModelAndView("/admin/users/list", "users", service.getAllUsers());
    }

    //    Add part mapping of Users
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/users/add";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        service.createUser(user);
        return "redirect:/admin/users";
    }

    //    Delete part mapping of Users
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteUserById(id);
        return "redirect:/admin/users";
    }

    //    Update part mapping of Users
    @GetMapping("/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") Long id) {
        return new ModelAndView("/admin/users/update", "user", service.getUserById(id));
    }

    //    @PostMapping("/update")
    @PutMapping("/update")
    public String updateUser(User user) {
        service.updateUser(user);
        return "redirect:/admin/users";
    }

    @ModelAttribute("maxId")
    public Long getMaxId() {
        return service.getMaxId();
    }

    @ModelAttribute("newUsers")
    public ModelAndView newUsers() {
        return new ModelAndView("/admin/users/list", "users", service.getAllUsers());
    }

}