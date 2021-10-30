package avadamedia.kinocms.controller.pages;

import avadamedia.kinocms.model.common.FileUploadUtil;
import avadamedia.kinocms.model.common.Image;
import avadamedia.kinocms.model.common.SEO;
import avadamedia.kinocms.model.pages.ContactsPage;
import avadamedia.kinocms.model.pages.NewPage;
import avadamedia.kinocms.model.pages.assist.CinemaContactsBlock;
import avadamedia.kinocms.service.common.ImageService;
import avadamedia.kinocms.service.page.ContactsPageService;
import avadamedia.kinocms.service.page.assist.CinemaContactsBlockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/pages/contacts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactsPageController {

    private final ContactsPageService contactsPageService;
    private final CinemaContactsBlockService cinemaContactsBlockService;

    // Update
    @GetMapping("edit")
    public ModelAndView showEditContactsPagePage() {
        ModelAndView mav = new ModelAndView("admin/pages/contacts/index");
        mav.addObject("contactsPage", contactsPageService.getContactsPageById(1L));
        return mav;
    }

    @PutMapping("update")
    public String updateContactsPagePage(@ModelAttribute("contactsPage") ContactsPage contactsPage) {
        contactsPage.setCinemaContactsBlocks(contactsPageService.getContactsPageById(1L).getCinemaContactsBlocks());
        contactsPageService.updateContactsPage(contactsPage);
        return "redirect:/admin/pages/contacts/edit";
    }

    // Add cinemaContactsBlock
    @GetMapping("edit/addContactsBlock")
    public String addCinemaContactsBlock() {
        CinemaContactsBlock cinemaContactsBlock = new CinemaContactsBlock();
        cinemaContactsBlock.setContactsPageId(contactsPageService.getLastId());
        cinemaContactsBlockService.createCinemaContactsBlock(new CinemaContactsBlock());
        return "redirect:/admin/pages/contacts/edit/contactsBlock/edit/" + cinemaContactsBlockService.getLastId();
    }

    // Update cinemaContactsBlock
    @GetMapping("edit/contactsBlock/edit/{id}")
    public ModelAndView showEditCinemaContactsBlockPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/pages/contacts/block/update");
        mav.addObject("cinemaContactsBlock", cinemaContactsBlockService.getCinemaContactsBlockById(id));
        return mav;
    }

    @PutMapping("edit/contactsBlock/update/{id}")
    public String updateCinemaContactsBlock(@PathVariable("id") Long id,
                                @ModelAttribute("cinemaContactsBlock") CinemaContactsBlock cinemaContactsBlock,
                                @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "contacts-pages/cinemaContactsBlocks/" + id;
        if (!fileName.equals("")) {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            cinemaContactsBlock.setMainImage(fileName);
        } else cinemaContactsBlock.setMainImage(cinemaContactsBlockService.getCinemaContactsBlockById(id).getMainImage());
        cinemaContactsBlock.setContactsPageId(1L);
        cinemaContactsBlockService.updateCinemaContactsBlock(cinemaContactsBlock);
        return "redirect:/admin/pages/contacts/edit/contactsBlock/edit/" + id;
    }

    // Delete cinemaContactsBlock
    @GetMapping("edit/contactsBlock/delete/{id}")
    public String deleteCinemaContactsBlock(@PathVariable("id") Long id) {
        cinemaContactsBlockService.deleteCinemaContactsBlockById(id);
        return "redirect:/admin/pages";
    }

}