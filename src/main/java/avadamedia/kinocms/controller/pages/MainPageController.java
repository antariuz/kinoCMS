package avadamedia.kinocms.controller.pages;

import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.pages.MainPage;
import avadamedia.kinocms.model.promotions.Promotion;
import avadamedia.kinocms.service.page.MainPageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/pages/main")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageController {

    private final MainPageService service;

    // Update
    @GetMapping("edit")
    public ModelAndView showEditMainPagePage() {
        ModelAndView mav = new ModelAndView("admin/pages/main/update");
        mav.addObject("mainPage", service.getMainPageById(1L));
        return mav;
    }

    @PutMapping("update")
    public String updateMainPage(@ModelAttribute("mainPage") MainPage mainPage) {
        service.updateMainPage(mainPage);
        return "redirect:/admin/pages/main/edit";
    }

}