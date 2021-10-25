package avadamedia.kinocms.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/mailings")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MailingsController {

//    private final MainPageService mainPageService;
//    private final ContactsPageService contactsPageService;

    // Show all
    @GetMapping({"/", ""})
    public ModelAndView showAllMailings() {
        ModelAndView mav = new ModelAndView("/admin/mailings/index");
//        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
//        mav.addObject("aboutCinemaPage", newPageService.getAllNewPages());
        return mav;
    }


}
