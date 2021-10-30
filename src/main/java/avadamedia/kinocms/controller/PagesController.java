package avadamedia.kinocms.controller;

import avadamedia.kinocms.model.pages.NewPage;
import avadamedia.kinocms.service.page.ContactsPageService;
import avadamedia.kinocms.service.page.MainPageService;
import avadamedia.kinocms.service.page.NewPageService;
import lombok.AllArgsConstructor;
import org.codehaus.groovy.util.ArrayIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("admin/pages")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PagesController {

    private final MainPageService mainPageService;
    private final ContactsPageService contactsPageService;
    private final NewPageService newPageService;

    // Show all
    @GetMapping({"/", ""})
    public ModelAndView showAllPages() {
        ModelAndView mav = new ModelAndView("admin/pages/index");
        mav.addObject("mainPage", mainPageService.getMainPageById(1L));
        mav.addObject("contactsPage", contactsPageService.getContactsPageById(1L));
        mav.addObject("requiredPages", newPageService.getAllNewPages());
        mav.addObject("newPages", newPageService.getAllNewPages());
        return mav;
    }

}
