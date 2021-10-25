package avadamedia.kinocms.controller;

import avadamedia.kinocms.service.page.ContactsPageService;
import avadamedia.kinocms.service.page.MainPageService;
import avadamedia.kinocms.service.page.NewPageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/pages")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PagesController {

    private final MainPageService mainPageService;
    private final ContactsPageService contactsPageService;
    private final NewPageService newPageService;

    //    Show all
    @GetMapping({"/", ""})
    public ModelAndView showAllPages() {
        ModelAndView mav = new ModelAndView("/admin/pages/index");
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("aboutCinemaPage", newPageService.getAllNewPages());
        mav.addObject("cafeBarPage", newPageService.getAllNewPages());
        mav.addObject("vipHallPage", newPageService.getAllNewPages());
        mav.addObject("childrenRoomPage", newPageService.getAllNewPages());
        mav.addObject("contactsPage", contactsPageService.getContactsPageById(1L));
        mav.addObject("newPages", newPageService.getAllNewPages());
        return mav;
    }


}
