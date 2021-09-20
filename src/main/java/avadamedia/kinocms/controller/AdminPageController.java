package avadamedia.kinocms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @RequestMapping({"/dashboard", "/", ""})
    public String showAdminPage(){
        return "admin/dashboard";
    }

}
