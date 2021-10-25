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
@RequestMapping("admin/pages/someshit")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageController {

    private final MainPageService service;

    @GetMapping("add")
    public String addPromotion(Model model) {
        model.addAttribute("promotion", new Promotion());
        model.addAttribute("seo", new SEO());
        return "/admin/promotions/add";
    }

    @PostMapping("add")
    public String addCurrentMovie(MainPage mainPage) {
        service.createMainPage(mainPage);
        return "redirect:/admin/promotions";
    }

    //    Delete part
    @GetMapping("delete/{id}")
    public String deleteCurrentMovie(@PathVariable("id") Long id) {
        service.deleteMainPageById(id);
        return "redirect:/admin/promotions";
    }

    //    Update part
    @GetMapping("update/{id}")
    public ModelAndView updateCurrentMovie(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("/admin/promotions/update");
        mav.addObject("promotion", service.getMainPageById(id));
        mav.addObject("seo", new SEO());
        return mav;
    }

    @PutMapping("update")
    public String updateCurrentMovie(MainPage mainPage) {
        service.updateMainPage(mainPage);
        return "redirect:/admin/promotions";
    }

}